package com.projectMVC;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
        public static void main(String args[]){


                ApiContextInitializer.init();

                TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

            ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);

                try {
                        telegramBotsApi.registerBot((TelegramLongPollingBot)context.getBean("infernalSatanBot"));
                } catch (TelegramApiRequestException e) {
                        e.printStackTrace();
                }
                System.out.println("===============================START projectMVC============================>>>>");
        }
}
