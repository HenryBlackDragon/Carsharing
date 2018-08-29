package com.alex.test.controller;

import com.alex.test.constans.Constants;
import com.alex.test.model.Role;
import com.alex.test.services.CarService;
import com.alex.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin_panel")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminPanelController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping
    public String getAdminPanel(Model model) {
        model.addAttribute("findAllUsers", userService.findAllUsers());
        model.addAttribute("roleAdmin", Role.ADMIN);
        model.addAttribute("findAllCars", carService.getAllCars());
        model.addAttribute("findAllNotActiveCars", carService.getAllNotActiveCars());

        return "admin_panel";
    }

    @PostMapping(params = "activate_car")
    public String getPreview(Long carId, Model model) {
        model.addAttribute("oneCarById", carService.getCarById(carId));

        return "preview";
    }

    @PostMapping(params = "delete")
    public String deleteAd(Long carId) {
        carService.deleteAd(carId);

        return "redirect:/admin_panel";
    }

    @PostMapping(params = "active")
    public String activateAdCar(Long carId) {
        carService.activateOrRefuseCar(carId, Constants.ACTIVATE_CAR);

        return "redirect:/admin_panel";
    }

    @PostMapping(params = "refus")
    public String refuseAdCar(Long carId) {
        carService.activateOrRefuseCar(carId, Constants.REFUSE_CAR);

        return "redirect:/admin_panel";
    }

}
