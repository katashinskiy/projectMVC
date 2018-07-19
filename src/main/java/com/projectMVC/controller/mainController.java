package com.projectMVC.controller;


import com.projectMVC.entity.Massege;
import com.projectMVC.entity.User;
import com.projectMVC.repository.MaseggeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;

@Controller
public class mainController {

    @Autowired
    private MaseggeRepository maseggeRepositiry;


    @GetMapping("/")
     public String greeting(Map<String,Object> model){
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Massege> masseges;
        if(filter != null && filter.isEmpty()){
            masseges = maseggeRepositiry.findAll();
        }else{
            masseges = maseggeRepositiry.findByTag(filter);
        }


        model.addAttribute("masseges", masseges);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Model model){

        Massege massege = new Massege(text,tag, user);
        maseggeRepositiry.save(massege);


        Iterable<Massege> massegesAll = maseggeRepositiry.findAll();
        model.addAttribute("masseges", massegesAll);
        return "main";
    }

}

