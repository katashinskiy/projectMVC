package com.projectMVC.repository;

import com.projectMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUserName(String name);
    User findByActivationCode(String code);
}
