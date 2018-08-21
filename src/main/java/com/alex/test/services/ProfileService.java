package com.alex.test.services;

import com.alex.test.model.User;
import com.alex.test.model.UserInfo;
import com.alex.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User refreshProfile(User user, String username, String phone) {
        User userFromDB = userRepository.findUserByEmail(user.getEmail());

        if (userFromDB == null) {
            return new User();
        }

        if ((StringUtils.isEmpty(username) && StringUtils.isEmpty(phone)) ||
                (StringUtils.isEmpty(username) && StringUtils.isEmpty(phone))) {
            return new User();
        }

        userFromDB.setUsername(username);

        if (userFromDB.getUserInfo() == null) {
            userFromDB.setUserInfo(UserInfo.builder()
                    .phoneNumber(phone)
                    .user(userFromDB)
                    .build());
        } else {
            userFromDB.getUserInfo().setPhoneNumber(phone);
        }

        userRepository.save(userFromDB);

        return userFromDB;
    }

    private User refreshProfileInfo() {
        return new User();
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

}
