package com.projectMVC.controller;


import com.projectMVC.entity.Massege;
import com.projectMVC.repository.MaseggeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String main(Map<String,Object> model){
        Iterable<Massege> masseges = maseggeRepositiry.findAll();
        model.put("masseges", masseges);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String,Object> model){
        Massege massege = new Massege(text,tag);
        maseggeRepositiry.save(massege);

        Iterable<Massege> masseges = maseggeRepositiry.findAll();
        model.put("masseges", masseges);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        Iterable<Massege> masseges;
        if(filter == ""){
            masseges = maseggeRepositiry.findAll();
        }else{
            masseges = maseggeRepositiry.findByTag(filter);
        }

        model.put("masseges", masseges);
        return "main";
    }
}

