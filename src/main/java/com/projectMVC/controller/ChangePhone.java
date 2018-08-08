package com.projectMVC.controller;

import com.projectMVC.entity.User;
import com.projectMVC.service.SMSService.SMSCSender;
import com.projectMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangePhone {

    @Autowired
    private UserService userService;

    @GetMapping("/changePhone")
    public String newNumber(@AuthenticationPrincipal User user,
                            Model model){
        User userUp = userService.findByUserName(user.getUsername());
        model.addAttribute("oldPhone", userUp.getPhone());
        return "changePhone";
    }


    @GetMapping("/changePhone/number")
    public String newPhone(@RequestParam("newPhone") String newPhone,
                           @RequestParam("confirmationCode") String confCode,
                           @AuthenticationPrincipal User user,
                           Model model
                           ){

        User user1 = userService.findByUserName(user.getUsername());
        if(user1.getConfirmCode().equals(confCode)){
            user1.setPhone(newPhone);
            userService.update(user1);
            model.addAttribute("messageSuccess", "number changed successfully");
            return "changePhone";
        }

        model.addAttribute("messageError", "number changed error");
        model.addAttribute("oldPhone", user.getPhone());
        return "changePhone";
    }

    @GetMapping("/getCode")
    public String getCode(@RequestParam("phone") String phone,
                          @AuthenticationPrincipal User user,
                          Model model){


        if(user.getPhone().matches("380.........")){
            SMSCSender sms  = new SMSCSender();
            Integer code = (int) (Math.random() * 8999) + 1000;

            sms.sendSms(user.getPhone(), "Ваш код: " + code.toString(), 1, "", "", 0, "", "");
            user.setConfirmCode(code.toString());
            userService.update(user);

        }else {
            model.addAttribute("phoneError", "Input correct cell phone number");
        }

        return "redirect:/changePhone";
    }
}
