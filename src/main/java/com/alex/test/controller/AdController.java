package com.alex.test.controller;

import com.alex.test.model.Car;
import com.alex.test.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

import static com.alex.test.controller.UtilsController.getUserFromSecurityContextHolder;

@Controller
public class AdController {

    @Autowired
    private AdService adService;


    @GetMapping("/new_ad")
    public String getNewAd() {
        return "new_ad";
    }

    @PostMapping("/new_ad")
    public String createNewAd(@RequestParam("carPhotos") MultipartFile[] file,
                              @Valid Car car,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilsController.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);

            return "new_ad";
        }

        adService.createNewAd(file, getUserFromSecurityContextHolder().getEmail(), car);

        model.addAttribute("", "");

        return "redirect:/new_ad";
    }

}
