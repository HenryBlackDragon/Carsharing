package com.alex.test.services;

import com.alex.test.model.Car;
import com.alex.test.model.Photo;
import com.alex.test.model.User;
import com.alex.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private UserRepository userRepository;

    public void createNewAd(MultipartFile[] files, String email, Car car) {
        User userByEmail = userRepository.findUserByEmail(email);

        car.setUserCar(userByEmail);
        car.setPhotos(photoList(files, userByEmail.getUsername(), car));

        userByEmail.getCars().add(car);

        userRepository.save(userByEmail);

    }

    private List<Photo> photoList(MultipartFile[] files, String username, Car car) {
        List<Photo> photos = new ArrayList<>();

        if (files != null) {
            String fullPath = uploadPath + "/" + username + "/cars/" + car.getMark() + "_" + car.getModel();

            Arrays.stream(files).forEach(file -> {
                try {

                    if (!StringUtils.isEmptyOrWhitespace(file.getOriginalFilename())) {
                        photos.add(Photo.builder()
                                .path(UtilsService.addCarPhotoToDir(file, fullPath))
                                .photoCars(car)
                                .build());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        return photos;
    }
}
