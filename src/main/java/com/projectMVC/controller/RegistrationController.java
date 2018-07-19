package com.projectMVC.controller;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username, @RequestParam String password, Map<String,Object> model){

        User user = new User();
        if(userRepository.findByUserName(username) != null){
            model.put("message", "User exist!!!");
            return "/registration";
        }
        if(username != null || password != null){
            user.setUserName(username);
            user.setUserPassword(password);
            user.setUserActive(true);
            user.setRole(Collections.singleton(Role.USER));
            userRepository.save(user);
        }

        return "redirect:/login";
    }
}
