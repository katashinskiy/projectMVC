package com.projectMVC.controller;


import com.projectMVC.entity.Massege;
import com.projectMVC.entity.User;
import com.projectMVC.repository.MaseggeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping()
public class MessageEditController {

    @Autowired
    private MaseggeRepository maseggeRepository;

    @GetMapping("/messages/{User}")
    public String UserMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("User") User user,
            @RequestParam(required = false) Massege message,
            Model model
    ) {
        Set<Massege> masseges = user.getMessages();


        model.addAttribute("messages", masseges);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "messages";
    }

    @PostMapping("/messages/{User}")
    public String EditMessage(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("id") Massege massege,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag
    ) {
        if(massege.getAuthor().equals(currentUser) || currentUser.isAdmin()){

            if(!StringUtils.isEmpty(text)){
                massege.setText(text);
            }

            if(!StringUtils.isEmpty(tag)){
                massege.setTag(tag);
            }

        }

        maseggeRepository.saveAndFlush(massege);
        return "redirect:/main";
    }
}
