package ru.naumen.cookingrecipesbot.config;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class BotConfig {
    private static final String BOT_NAME = "CookingRecipes196bot";
    private static final String BOT_TOKEN = "6568467477:AAH8ZFVCwN3e5YAvSNOXgm6qXQdXbqsPubk";

    public String getBOT_NAME() {
        return BOT_NAME;
    }

    public String getBOT_TOKEN() {
        return BOT_TOKEN;
    }
}
