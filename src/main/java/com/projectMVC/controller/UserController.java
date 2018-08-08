package com.projectMVC.controller;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.entity.Valid.ValidProfile;
import com.projectMVC.repository.UserRepository;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String userInf(Model model) {

        model.addAttribute("users", userService.findAll());
        return "Users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{userId}")
    public String userEditForm(@PathVariable Integer userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("User", user);
        model.addAttribute("Role", Role.values());
        return "UserEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String editUser(
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            @RequestParam String userName) {

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRole().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRole().add(Role.valueOf(key));
            }
        }

        user.setUsername(userName);

        userService.update(user);

        return "redirect:/Users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Integer id, Model model) {
        userService.delete(id);
        model.addAttribute("users", userService.findAll());
        return "Users";
    }

    @GetMapping("/subscribe/{userId}")
    public String subscribe(@AuthenticationPrincipal User currentUser,
                            @PathVariable("userId") User user
    ) {
        userService.subscribeUser(currentUser, user);
        return "redirect:/messages/" + currentUser.getId();
    }

    @GetMapping("/unsubscribe/{userId}")
    public String unsubscribe(@AuthenticationPrincipal User currentUser,
                              @PathVariable("userId") User user
    ) {
        userService.unsubscribeUser(currentUser, user);
        return "redirect:/messages/" + currentUser.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String UserList(
            @PathVariable("type") String type,
            @PathVariable("user") User user,
            Model model
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subscriptions";
    }


}
