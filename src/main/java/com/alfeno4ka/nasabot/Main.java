package com.alfeno4ka.nasabot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {

    private static final String ENV_BOT_TOKEN = "ENV_BOT_TOKEN";

    public static void main(String[] args) throws IOException, TelegramApiException {
        String botToken = System.getenv("BOT_TOKEN");
        MyTelegramBot bot1 = new MyTelegramBot("apod2_bot",botToken);
    }


}
