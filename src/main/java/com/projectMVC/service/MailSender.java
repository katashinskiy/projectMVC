package com.projectMVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String userName;

    public void sender(String emailTo, String subject, String mail){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(userName);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(mail);

        javaMailSender.send(mailMessage);

    }
}
