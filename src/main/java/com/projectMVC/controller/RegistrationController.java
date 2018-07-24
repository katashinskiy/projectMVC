package com.projectMVC.controller;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.repository.UserRepository;
import com.projectMVC.service.UserService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password, Model model){

        User user = new User();
        user.setUserPassword(password);
        user.setUserName(username);
        user.setEmail(email);
        if(!userService.addUser(user)){
            model.addAttribute("message","User exist!!!");

            return "/registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activatrdPage(@PathVariable String code, Model model){
        boolean isActivated = userService.isActivated(code);

        if(isActivated){
            model.addAttribute("message", "Activation Successful)))");
        }
        else{
            model.addAttribute("message", "Activation not successful(((");
        }
        return "login";
    }
}
