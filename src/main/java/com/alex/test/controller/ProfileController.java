package com.alex.test.controller;

import com.alex.test.model.DataPassport;
import com.alex.test.model.DrivingLicense;
import com.alex.test.model.Role;
import com.alex.test.model.User;
import com.alex.test.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

import static com.alex.test.controller.ControllerUtils.getUserFromSecurityContextHolder;
import static com.alex.test.controller.ControllerUtils.updateSecurityContextHolder;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/my_profile")
    public String getProfile(Model model) {
        model.addAttribute("user", getUserFromSecurityContextHolder());
        model.addAttribute("roleAdmin", Role.ADMIN);

        if (getUserFromSecurityContextHolder().getUserInfo().getPhoneNumber() != null) {
            model.addAttribute("phone", getUserFromSecurityContextHolder().getUserInfo().getPhoneNumber());
        }

        if (getUserFromSecurityContextHolder().getUserInfo().getDataPassport() != null) {
            model.addAttribute("passportData", getUserFromSecurityContextHolder().getUserInfo().getDataPassport());
        }

        if (getUserFromSecurityContextHolder().getUserInfo().getLicense() != null) {
            model.addAttribute("drivingLicense", getUserFromSecurityContextHolder().getUserInfo().getLicense());
        }

        return "my_profile";
    }

    @GetMapping("/settings")
    public String getSettings() {
        return "settings";
    }

    @GetMapping("/passport")
    public String getDataPassport() {
        return "data_passport";
    }

    @GetMapping("/driver_license")
    public String getDriverLicense() {
        return "driver_license";
    }

    @PostMapping(value = "/my_profile", params = "update_profile")
    public String refreshProfile(String username, String phone, Model model) {
        if (!profileService.checkUsername(username)) {
            model.addAttribute("userExistsError", "Такой пользователь уже существует");

            return getProfile(model);
        }

        User userFromService = profileService.refreshProfile(getUserFromSecurityContextHolder(), username, phone);

        if (userFromService.getUserInfo() == null) {
            return getProfile(model);
        }

        updateSecurityContextHolder(userFromService);

        model.addAttribute("username", getUserFromSecurityContextHolder().getUsername());
        model.addAttribute("phone", getUserFromSecurityContextHolder().getUserInfo().getPhoneNumber());

        return getProfile(model);
    }

    @PostMapping(value = "/settings", params = "update_password")
    public String refreshPas(String oldPassword, String newPassword, String confirmPassword, Model model) {
        if (!profileService.checkPassword(oldPassword, getUserFromSecurityContextHolder().getPassword())) {
            model.addAttribute("passwordError", "Пароль не совпадает");

            return "settings";
        }

        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)) {
            model.addAttribute("passwordEmptyError", "Пароли не должны быть пустыми");

            return "settings";
        } else if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("passwordDifferentError", "Пароли не совпадает");

            return "settings";
        }

        updateSecurityContextHolder(profileService.refreshPassword(
                getUserFromSecurityContextHolder().getEmail(), newPassword));

        model.addAttribute("success", " Пароль был успешно изменен");

        return "settings";
    }

    @PostMapping(value = "/passport", params = "data_passport")
    public String addDataPassport(@Valid DataPassport dataPassport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);

            return "data_passport";
        }

        User user = profileService.addPassport(dataPassport, getUserFromSecurityContextHolder().getEmail());

        updateSecurityContextHolder(user);

        model.addAttribute("success", " Паспорт успешно добавлен");

        return "data_passport";
    }

    @PostMapping(value = "/driver_license", params = "driver_license")
    public String addDriverLicense(@Valid DrivingLicense drivingLicense, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);

            return "driver_license";
        }

        User user = profileService.addDriverLicense(drivingLicense, getUserFromSecurityContextHolder().getEmail());

        updateSecurityContextHolder(user);

        model.addAttribute("success", " Водительское удостоверение успешно добавлено");

        return "driver_license";
    }

//    @PostMapping(value = "/my_profile", params = "photo")
//    public String addPhoto(@RequestParam("files") MultipartFile file, Model model) throws IOException {
//
//        User find = getUserFromSecurityContextHolder();
//        if(file.getOriginalFilename().equals("")){
//
//            return "";
//        }
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
