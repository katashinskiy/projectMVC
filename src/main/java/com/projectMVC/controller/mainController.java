package com.projectMVC.controller;


import com.projectMVC.entity.Massege;
import com.projectMVC.entity.User;
import com.projectMVC.repository.MaseggeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class mainController {

    @Autowired
    private MaseggeRepository maseggeRepositiry;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Massege> masseges;
        if (filter != null && filter.isEmpty()) {
            masseges = maseggeRepositiry.findAll();
        } else {
            masseges = maseggeRepositiry.findByTag(filter);
        }


        model.addAttribute("messages", masseges);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Massege massege,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> collect = ControllerUtils.getError(bindingResult);

            model.mergeAttributes(collect);
            model.addAttribute("message", massege);
        } else {
            massege.setAuthor(user);
            maseggeRepositiry.save(massege);
        }


        Iterable<Massege> massegesAll = maseggeRepositiry.findAll();
        model.addAttribute("messages", massegesAll);
        return "main";
    }

}

