package com.alex.test.controller;

import com.alex.test.model.Car;
import com.alex.test.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.alex.test.controller.UtilsController.getAuthentication;

@Controller
public class MainController {

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public String getMainPage(Model model) {
        List<Car> cars = carService.getAllActiveCars();
        if (!CollectionUtils.isEmpty(cars)) {
            model.addAttribute("cars", cars);
        }

        return "mainpage";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "auth/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
