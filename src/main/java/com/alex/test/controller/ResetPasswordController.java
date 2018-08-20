package com.alex.test.controller;

import com.alex.test.model.User;
import com.alex.test.services.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService passwordService;

    @GetMapping("/reset-password")
    public String getForgetPassword() {
        return "reset-password-email";
    }

    @PostMapping("/reset-password")
    public String getForgetPassword(User user, Model model) {
        if (!passwordService.sendCodeForResetPassword(user.getEmail())) {
            model.addAttribute("notFindEmailError", "Can't find that email.");

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

    @PostMapping("/activate-reset/{code}")
    public String changePassword(
            @RequestParam("password2") String passwordConfirm,
            @PathVariable String code,
            String password,
            Model model) {

        if (StringUtils.isEmpty(password) && StringUtils.isEmpty(passwordConfirm)) {
            model.addAttribute("passwordError", "Password cannot be empty");
            model.addAttribute("confirmPasswordError", "Password confirmation cannot be empty");

            return "reset-password";
        }

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("differentPasswordError", "Passwords are different");

            return "reset-password";
        }

        passwordService.changePassword(code, password);

        return "redirect:/login";
    }
}
