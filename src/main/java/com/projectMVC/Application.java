package com.projectMVC;


import com.projectMVC.telegramBot.InfernalSatanBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class Application {
        public static void main(String args[]){
                SpringApplication.run(Application.class,args);

                ApiContextInitializer.init();

                TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

                try {
                        telegramBotsApi.registerBot(new InfernalSatanBot());
                } catch (TelegramApiRequestException e) {
                        e.printStackTrace();
                }
        }
}
