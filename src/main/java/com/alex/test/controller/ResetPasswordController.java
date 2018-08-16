package com.alex.test.controller;

import com.alex.test.model.User;
import com.alex.test.services.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService passwordService;

    @GetMapping("/reset-password")
    public String getForgetPassword() {
        return "reset-password-email";
    }

    @PostMapping("/reset-password")
    public String getForgetPassword(User user) {
        if (!passwordService.sendCodeForResetPassword(user.getEmail())) {
            return "reset-password-email";
        }

        return "redirect:/login";
    }

    @GetMapping(value = "/activate-reset/{code}")
    public String activate(@PathVariable String code) {
        if (passwordService.activeResetPassword(code)) {
            return "reset-password";
        } else {
            return "redirect:/login";
        }
    }
}
