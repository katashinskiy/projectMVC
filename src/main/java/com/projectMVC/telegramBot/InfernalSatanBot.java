package com.projectMVC.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class InfernalSatanBot  extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "645563632:AAFmJVTTeIiwuBKiLGkP75T9VJXIagMAF6Q";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){

            long chat_id = update.getMessage().getChatId();

            String message_text = update.getMessage().getText();

            if(message_text.equals("/pic")){

                SendPhoto sendPhoto = new SendPhoto()
                        .setChatId(chat_id)
                        .setPhoto("AgADAgADN6kxG483YEtPTvTMd05JI8dWqw4ABCPQ8ykR3TkxCnoEAAEC")
                        .setCaption("Harlamov");

                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if(message_text.equals("/keyboard")){

                SendMessage sendMessage = new SendMessage()
                        .setChatId(chat_id)
                        .setText("This is you keyboard");

                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

                List<KeyboardRow> keyboardRows = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();

                row.add("Button 1");
                row.add("Button 2");
                row.add("Button 3");

                keyboardRows.add(row);

                row = new KeyboardRow();

                row.add("Button 4");
                row.add("Button 5");
                row.add("Button 6");

                keyboardRows.add(row);

                keyboardMarkup.setKeyboard(keyboardRows);

                sendMessage.setReplyMarkup(keyboardMarkup);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            }else
             {

                SendMessage sendMessage = new SendMessage()
                        .setChatId(chat_id)
                        .setText("I don't know what do you want from me");

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
        else if(update.hasMessage() && update.getMessage().hasPhoto()){
            long chat_id = update.getMessage().getChatId();

            List<PhotoSize> photos = update.getMessage().getPhoto();

            String file_id = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null)
                    .getFileId();


            int file_width = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null)
                    .getWidth();

            int file_height = photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null)
                    .getHeight();

            String caption = "file_id : " + file_id + "\n file_width : " + file_width
                    + " \n file_height : " + file_height;

            SendPhoto sendP = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(file_id)
                    .setCaption(caption);

            try {
                execute(sendP);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "InfernalSatan_bot";
    }



}
