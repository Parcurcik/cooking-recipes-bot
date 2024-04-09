package ru.naumen.cookingrecipesbot.config;


public class BotConfig {
    private static final String BOT_NAME = System.getenv("BOT_NAME");
    private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");

    public String getBOT_NAME() {
        return BOT_NAME;
    }

    public String getBOT_TOKEN() {
        return BOT_TOKEN;
    }
}
