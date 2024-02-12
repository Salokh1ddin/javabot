package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class SalakhBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.hasText()) {
                if (message.getText().equalsIgnoreCase("/start")) {
                    SendMessage sendMessage = new SendMessage(
                            message.getChatId().toString(),
                            "Assalomu alaykum"
                    );

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (message.hasPhoto()) {
                System.out.println(update);
                List<PhotoSize> photoList = message.getPhoto();
                System.out.println(photoList);
                for (PhotoSize photoSize : photoList) {
                    System.out.println(photoSize);
                }
                String caption = message.getCaption();

                PhotoSize photoSize = photoList.get(photoList.size() - 1);
                photoSize.getFileId();
                String fileId = photoSize.getFileId();

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(message.getChatId());
                sendPhoto.setPhoto(new InputFile(fileId));
                sendPhoto.setCaption(caption + " nma qlay o'zing pishirgan osh ozin yegin");

                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    @Override
    public String getBotUsername() {
        return "@java_kod_bot" ;
    }
    public String getBotToken(){
        return "6911106121:AAH0aIG9dKVShKThuuUKWTvHGQh1OjH-77c";
    }

}