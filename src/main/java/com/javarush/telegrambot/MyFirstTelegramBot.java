package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_test_student_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6874183100:AAFS3WxLEUmxE5W_hGu9T1kb6DAW6052xvQ"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // TODO: основной функционал бота будем писать здесь
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1"));
        }

        if (getCallbackQueryButtonKey().equals("step_1")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
               Map.of( "Взять сосиску! +20 славы", "step_2",
                    "Взять рыбку! +20 славы", "step_2",
                    "Взять пиво! +20 славы", "step_2"));
        }

        if (getCallbackQueryButtonKey().equals("step_2")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота-пылесоса", "step_3"));
        }

        if (getCallbackQueryButtonKey().equals("step_3")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of( "Отправить робот пылесос за едой! +30 славы", "step_4",
                            "Покататься на работе пылесосе! +30 славы", "step_4",
                            "Убежать от робота пылесоса! +30 славы", "step_4"));
        }

        if (getCallbackQueryButtonKey().equals("step_4")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5"));
        }

        if (getCallbackQueryButtonKey().equals("step_5")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of( "Снять на видео как ты кушаешь рыбку! +30 славы", "step_6",
                            "Покушать рыбку и снять это на видео! +30 славы", "step_6",
                            "Насладиться рыбкой, снимая это на видео! +30 славы", "step_6"));
        }

        if (getCallbackQueryButtonKey().equals("step_6")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля на компьютере!", "step_7"));
        }

        if (getCallbackQueryButtonKey().equals("step_7")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти похвастаться во двор!", "step_8"));

        }

        if (getCallbackQueryButtonKey().equals("step_8")) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }

        if (getMessageText().equals("/glory")) {
            sendTextMessageAsync("Твоя слава среди кошек равна: " + getUserGlory());
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}