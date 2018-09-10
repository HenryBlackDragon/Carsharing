package com.alex.test.controller;

import com.alex.test.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UtilsController {
    static Map<String, String> getErrorsMap(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );

        return bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
    }

    public static User getUserFromSecurityContextHolder() {
        return (User) getAuthentication().getPrincipal();
    }

    static void updateSecurityContextHolder(User user) {
        Authentication newAuth = new UsernamePasswordAuthenticationToken(user, getAuthentication().getCredentials(),
                new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
