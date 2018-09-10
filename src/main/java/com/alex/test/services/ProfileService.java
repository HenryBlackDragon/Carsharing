package com.alex.test.services;

import com.alex.test.model.DataPassport;
import com.alex.test.model.DrivingLicense;
import com.alex.test.model.User;
import com.alex.test.repository.UserRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProfileService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User refreshProfile(User user, String username, String phone) {
        User userFromDB = userRepository.findUserByEmail(user.getEmail());

        if (userFromDB == null || (StringUtils.isEmpty(username) && StringUtils.isEmpty(phone))) {
            return new User();
        }

        userFromDB.setUsername(username);
        userFromDB.getUserInfo().setPhoneNumber(phone);

        userRepository.save(userFromDB);

        return userFromDB;
    }

    public User refreshPassword(String email, String newPassword) {
        User userFromDB = userRepository.findUserByEmail(email);

        userFromDB.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userFromDB);

        return userFromDB;
    }

    private User refreshEmail() {
        return new User();
    }


    public boolean checkPassword(String oldPassword, String userPassword) {
        return passwordEncoder.matches(oldPassword, userPassword);
    }

    public User addPassport(DataPassport dataPassport, String email) {
        User userByEmail = userRepository.findUserByEmail(email);

        userByEmail.getUserInfo()
                .setDataPassport(
                        DataPassport.builder()
                                .surname(dataPassport.getSurname())
                                .name(dataPassport.getName())
                                .patronymic(dataPassport.getPatronymic())
                                .series(dataPassport.getSeries())
                                .numPassport(dataPassport.getNumPassport())
                                .organizationIssued(dataPassport.getOrganizationIssued())
                                .dateIssued(dataPassport.getDateIssued())
                                .dateBorn(dataPassport.getDateBorn())
                                .placeBorn(dataPassport.getPlaceBorn())
                                .userInfoPassport(userByEmail.getUserInfo())
                                .build()
                );

        userRepository.save(userByEmail);

        return userByEmail;
    }

    public boolean checkUsername(String username) {
        return userRepository.findUserByUsername(username) == null;
    }

    public User addDriverLicense(DrivingLicense drivingLicense, String email) {
        User userByEmail = userRepository.findUserByEmail(email);

        userByEmail.getUserInfo()
                .setLicense(
                        DrivingLicense.builder()
                                .series(drivingLicense.getSeries())
                                .numLicense(drivingLicense.getNumLicense())
                                .dateIssued(drivingLicense.getDateIssued())
                                .organizationIssued(drivingLicense.getOrganizationIssued())
                                .dateEnd(drivingLicense.getDateEnd())
                                .category(drivingLicense.getCategory())
                                .userInfoDriverLicense(userByEmail.getUserInfo())
                                .build()
                );

        userRepository.save(userByEmail);

        return userByEmail;
    }

    public void updateUserPhoto(MultipartFile file, User user) throws IOException {
        String fullPath = uploadPath + "/" + user.getUsername() + "/userphoto";

        user.getUserInfo().setPhoto(UtilsService.addUserPhotoToDir(file, fullPath));

        userRepository.save(user);
    }
}
