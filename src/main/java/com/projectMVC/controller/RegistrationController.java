package com.projectMVC.controller;


import com.projectMVC.entity.User;
import com.projectMVC.entity.dto.CaptchaResponse;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @RequestParam("password2") String password2,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        String url = String.format(CAPTCHA_URL,secret,captchaResponse);

        CaptchaResponse response = restTemplate.postForObject(url,Collections.emptyList(),CaptchaResponse.class);

        model.addAttribute("user", user);

        if(!response.isSuccess()){
            model.addAttribute("captchaError","Fill captcha");
        }

        if(password2 == null || password2.isEmpty()){
            model.addAttribute("password2Error", "Confirm password can't be empty");
        }
        if (user.getPassword() != null && !user.getPassword().equals(password2)) {
            model.addAttribute("passwordError", "Passwords are different!");
            return "registration";
        }

        if (bindingResult.hasErrors() || !response.isSuccess()) {
            model.mergeAttributes(ControllerUtils.getError(bindingResult));
            return "registration";
        }


        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exist!!!");

            return "registration";
        }



        return "redirect:/login";
    }

    @GetMapping("/registration/activate/{code}")
    public String activatrdPage(@PathVariable String code, Model model) {
        boolean isActivated = userService.isActivated(code);

        if (isActivated) {
            model.addAttribute("message", "Activation Successful)))");
        } else {
            model.addAttribute("messageErr", "Activation not Successful(((");
        }
        return "login";
    }
}
