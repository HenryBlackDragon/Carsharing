package com.alex.test.services;

import com.alex.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailSender {

    @Autowired
    @Qualifier("getMailSender")
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userName;

    public void sendMessageToUser(String emailTo, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(userName);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);
    }

//    public void sendMessageToUser(String email, String, subject String message) {
//        sendMessageToUser(email, subject, message);
//
//
//
//
//        mailSender.sendMessageToUser(user.getEmail(), "Activation activationCodeRepository", msg);
//    }
}
