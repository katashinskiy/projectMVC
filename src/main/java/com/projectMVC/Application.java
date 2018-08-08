package com.projectMVC;


import com.projectMVC.service.SMSService.SMSCSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
        public static void main(String args[]){
                SpringApplication.run(Application.class,args);


//                SMSCSender sd= new SMSCSender();
//
//                sd.sendSms("380638816321", "Ваш пароль: fucking bitch ", 1, "", "", 0, "", "");
////                sd.getSmsCost("38*********5", "Вы успешно зарегистрированы!", 0, 0, "", "");
//                System.err.println("==============================>" + sd.getBalance());
        }
}
