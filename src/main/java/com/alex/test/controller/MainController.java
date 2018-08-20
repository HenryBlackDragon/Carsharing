package com.alex.test.controller;

import com.alex.test.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @RequestMapping("/")
    public String getMainPage(User user) {
        return "mainpage";
    }

    @RequestMapping("/my_profile")
    public String getProfile(User user) {
        return "my_profile";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }
}
