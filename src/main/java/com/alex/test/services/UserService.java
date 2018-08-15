package com.alex.test.services;

import com.alex.test.model.ActivationCode;
import com.alex.test.model.Role;
import com.alex.test.model.User;
import com.alex.test.repository.ActivationCodeRepository;
import com.alex.test.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // find by email
    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }


    public boolean addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setAuthorities(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(ActivationCode.builder()
                .code(UUID.randomUUID().toString())
                .user(user)
                .build());

        userRepository.save(user);

        sendMessageToUser(user);

        return true;
    }

    private void sendMessageToUser(User user){

        if (!StringUtils.isEmpty(user.getEmail())) {
            String msg = String.format(
                    "Hello, %s! Welcome to Carsharing. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getEmail(),
                    user.getActivationCode().getCode()
            );

            mailSender.send(user.getEmail(), "Activation activationCodeRepository", msg);
        }
    }

    public boolean activateUser(String code) {
        ActivationCode activationCodeFromDB = activationCodeRepository.findActivationCodeByCode(code);

        if (activationCodeFromDB == null || activationCodeFromDB.getUser() == null) {
            return false;
        } else {
            User user = activationCodeFromDB.getUser();

            activationCodeFromDB.setCode(null);
            user.setActive(true);

            activationCodeRepository.save(activationCodeFromDB);
            userRepository.save(user);
        }

        return true;
    }
}
