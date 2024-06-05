package ru.naumen.cookingrecipesbot.bot;

import org.springframework.stereotype.Component;

/**
 * Конфигурационные данные для бота
 */
@Component
public class BotConfig {
    private static final String BOT_NAME = "CookingRecipeesbot";
    private static final String BOT_TOKEN = "7137649601:AAEttqdYnNPza5WRCy_6hjNfuUqS4B32K-U";

    /**
     * Возвращает имя бота, заданное в телеграмме
     * @return
     */
    public String getBOT_NAME() {
        return BOT_NAME;
    }

    /**
     * Возвращает токен бота, заданный в телеграмме
     * @return
     */
    public String getBOT_TOKEN() {
        return BOT_TOKEN;
    }
}
