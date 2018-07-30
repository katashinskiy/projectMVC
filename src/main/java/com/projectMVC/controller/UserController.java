package com.projectMVC.controller;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.repository.UserRepository;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userInf(Model model){

        model.addAttribute("users",  userService.findAll());
        return "Users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{userId}")
    public String userEditForm(@PathVariable Integer userId, Model model){
        User user = userService.findById(userId);
        model.addAttribute("User",user);
        model.addAttribute("Role", Role.values());
        return "UserEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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


        user.setUsername(userName);

        userService.update(user);

        return "redirect:/Users";
    }



    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("Username", user.getUsername());
        model.addAttribute("email",user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String editInf(@RequestParam String email,
                          @RequestParam String password,
                          @AuthenticationPrincipal User user){

        userService.updateProfile(user,email,password);

        return "redirect:/main";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id,Model model)
    {
        userService.delete(id);
        model.addAttribute("users", userService.findAll());
        return "Users";
    }
}
