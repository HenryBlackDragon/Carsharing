package com.alex.test.controller;

import com.alex.test.model.Car;
import com.alex.test.model.User;
import com.alex.test.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

import static com.alex.test.controller.ControllerUtils.getUserFromSecurityContextHolder;
import static com.alex.test.controller.ControllerUtils.updateSecurityContextHolder;

@Controller
public class AdController {

    @Autowired
    private AdService adService;


    @GetMapping("/new_ad")
    public String getNewAd() {
        return "new_ad";
    }

    @PostMapping("/new_ad")
    public String createNewAd(@Valid Car car,
                              BindingResult bindingResult,
                              Model model) {
//        if (file.getOriginalFilename().equals("")) {
//            model.addAttribute("photoError", "");
//
//            return "new_add";
//        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);

            return "new_ad";
        }

        User user = adService.createNewAd(getUserFromSecurityContextHolder().getEmail(), car);

        updateSecurityContextHolder(user);

        model.addAttribute("", "");

        return "new_ad";
    }

//    @PostMapping(value = "/my_profile", params = "photo")
//    public String addPhoto(Model model) throws IOException {
//
//        User find = getUserFromSecurityContextHolder();
//
//
//        if (file != null) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFile = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + resultFile));
//
//            find.setPhoto(resultFile);
//            updateSecurityContextHolder(find);
//
//            userRepository.save(find);
//
//            User userFromSecurityContextHolder = getUserFromSecurityContextHolder();
//
//            model.addAttribute("user", userFromSecurityContextHolder);
//        }
//
//        return "my_profile";
//    }

}
