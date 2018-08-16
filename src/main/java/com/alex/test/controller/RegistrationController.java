package com.alex.test.controller;

import com.alex.test.model.User;
import com.alex.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);

            return "registration";
        }

        if (!userService.addUser(user, model)) {
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping(value = "/activate/{code}")
    public String activate(@PathVariable String code) {
        if (userService.activateUser(code)) {
            return "redirect_to_login";
        } else {
            return "not_active";
        }
    }
}
