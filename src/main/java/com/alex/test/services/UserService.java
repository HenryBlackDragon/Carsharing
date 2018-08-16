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
import org.springframework.ui.Model;

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
        return userRepository.findUserByEmail(email);
    }

    public boolean addUser(User user, Model model) {
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("usernameError", "The username is already in use");
            return false;
        } else if (userRepository.findUserByEmail(user.getEmail()) != null) {
            model.addAttribute("emailError", "This email address has been taken");
            return false;
        }

        user.setAuthorities(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(ActivationCode.builder()
                .code(UUID.randomUUID().toString())
                .user(user)
                .build());

        userRepository.save(user);

        String msg = String.format(
                "Hello, %s! Welcome to Carsharing. Please, visit next link: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode().getCode()
        );

        mailSender.sendMessageToUser(user.getEmail(), "Activation code", msg);

        return true;
    }


    public boolean activateUser(String code) {
        ActivationCode activationCodeFromDB = activationCodeRepository.findActivationCodeByCode(code);

        if (activationCodeFromDB == null || activationCodeFromDB.getUser() == null) {
            return false;
        } else {
            User user = activationCodeFromDB.getUser();

            user.setActivationCode(null);
            user.setActive(true);

            userRepository.save(user);
            activationCodeRepository.delete(activationCodeFromDB);
        }

        return true;
    }
}
