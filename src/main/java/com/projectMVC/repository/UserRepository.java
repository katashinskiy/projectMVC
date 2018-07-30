package com.projectMVC.repository;

import com.projectMVC.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    User findByUsername(String name);
    User findByActivationCode(String code);
    void deleteById(Integer id);
}
