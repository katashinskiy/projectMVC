package com.projectMVC.controller;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userInf(Model model){

        model.addAttribute("users",  userRepository.findAll());
        return "Users";
    }

    @GetMapping("{userId}")
    public String userEditForm(@PathVariable Integer userId, Model model){
        User user = userRepository.getOne(userId);
        model.addAttribute("User",user);
        model.addAttribute("Role", Role.values());
        return "UserEdit";
    }

    @PostMapping
    public String editUser(
            @RequestParam Map<String,String>  form,
            @RequestParam("userId") User user,
            @RequestParam String userName){

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRole().clear();

        for(String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRole().add(Role.valueOf(key));
            }
        }


        user.setUserName(userName);

        userRepository.saveAndFlush(user);

        return "redirect:/Users";
    }
}
