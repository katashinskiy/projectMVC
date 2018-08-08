package com.projectMVC.controller;


import com.projectMVC.entity.User;
import com.projectMVC.entity.Valid.ValidProfile;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserPrifileController {
    @Autowired
    private UserService userService;

    @GetMapping("/Users/profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {

        model.addAttribute("Username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/Users/profile")
    public String editInf(@AuthenticationPrincipal User user,
                          @Valid ValidProfile validProfile,
                          BindingResult bindingResult,
                          Model model) {

        if(bindingResult.hasErrors()){
            model.mergeAttributes(ControllerUtils.getError(bindingResult));
            model.addAttribute("email", validProfile.getEmail());
            model.addAttribute("password", validProfile.getPassword());
            model.addAttribute("password2", validProfile.getPassword2());
            return "profile";
        }else{
            userService.updateProfile(user, validProfile.getEmail(), validProfile.getPassword());
        }

        if((validProfile.getPassword() != null && !validProfile.getPassword().isEmpty()) &&
                (validProfile.getPassword2() != null && !validProfile.getPassword2().isEmpty()) &&
                (!validProfile.getPassword().equals(validProfile.getPassword2()))){
            model.addAttribute("email", validProfile.getEmail());
            model.addAttribute("passwordError", "Confirm password different from password");
            return "profile";
        }

        return "redirect:/main";
    }
}
