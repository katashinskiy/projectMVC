package com.projectMVC.service;


import com.projectMVC.entity.Role;
import com.projectMVC.entity.User;
import com.projectMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUserName(s);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUserName(user.getUserName());
        if(userFromDb != null){
            return false;
        }

        if(user.getUserName() != null || user.getUserPassword() != null || user.getEmail() != null){

            user.setUserActive(true);
            user.setRole(Collections.singleton(Role.USER));
            user.setActivationCode(UUID.randomUUID().toString());
            userRepository.save(user);
        }

        String message = String.format(
                "Hello %s \n " +
                        "Welcome to our Site. Please visit next link: http://localhost:8080/activate/%s",
                user.getUserName(),user.getActivationCode()
        );

        if(!StringUtils.isEmpty(user.getEmail())){
            mailSender.sender(user.getEmail(),"Activation Code", message);
        }
        return true;
    }

    public boolean isActivated(String code) {
        User user  = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);

        return true;
    }
}
