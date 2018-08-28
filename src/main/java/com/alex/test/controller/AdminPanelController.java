package com.alex.test.controller;

import com.alex.test.model.Role;
import com.alex.test.services.MainService;
import com.alex.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_panel")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {

    @Autowired
    private UserService userService;

    @Autowired
    private MainService mainService;

    @GetMapping
    public String getAdminPanel(Model model) {
        model.addAttribute("findAllUsers", userService.findAllUsers());
        model.addAttribute("roleAdmin", Role.ADMIN);
        model.addAttribute("findAllCars", mainService.getAllCars());

        return "admin_panel";
    }

}
