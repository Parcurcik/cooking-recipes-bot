package ru.naumen.cookingrecipesbot.bot;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
public class BotConfig {
    private static final String BOT_NAME = "CookingRecipeesbot";
    private static final String BOT_TOKEN = "7137649601:AAEttqdYnNPza5WRCy_6hjNfuUqS4B32K-U";

    public String getBOT_NAME() {
        return BOT_NAME;
    }

    public String getBOT_TOKEN() {
        return BOT_TOKEN;
    }
}
