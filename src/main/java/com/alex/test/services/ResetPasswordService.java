package com.alex.test.services;

import com.alex.test.model.ActivationCode;
import com.alex.test.model.User;
import com.alex.test.repository.ActivationCodeRepository;
import com.alex.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ResetPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean sendCodeForResetPassword(String email) {
        User userByEmail = userRepository.findUserByEmail(email);

        if (userByEmail == null) {
            return false;
        }

        userByEmail.setActivationCode(ActivationCode
                .builder()
                .code(UUID.randomUUID().toString())
                .user(userByEmail)
                .build());

        userRepository.save(userByEmail);

        String resetMsg = String.format(
                "Hello, %s! Please, visit next link to reset password: http://localhost:8080/activate-reset/%s",
                userByEmail.getUsername(),
                userByEmail.getActivationCode().getCode()
        );

        mailSender.sendMessageToUser(userByEmail.getEmail(), "Reset password", resetMsg);

        return true;
    }

    public boolean activeResetPassword(String code) {
        ActivationCode activationCodeFromDB = activationCodeRepository.findActivationCodeByCode(code);

        return activationCodeFromDB != null && activationCodeFromDB.getUser() != null;
    }

    public void changePassword(String code, String password) {
        ActivationCode activationCodeFromDB = activationCodeRepository.findActivationCodeByCode(code);

        User user = activationCodeFromDB.getUser();

        user.setPassword(passwordEncoder.encode(password));
        user.setActivationCode(null);

        userRepository.save(user);
        activationCodeRepository.delete(activationCodeFromDB);
    }
}
