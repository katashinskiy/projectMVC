package com.projectMVC.repository;


import com.projectMVC.entity.MessageFromBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageFromBotRepository extends JpaRepository<MessageFromBot,Long >{

}
