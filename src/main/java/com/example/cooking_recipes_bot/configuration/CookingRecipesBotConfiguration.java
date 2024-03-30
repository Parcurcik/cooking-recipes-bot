package com.example.cooking_recipes_bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.example.cooking_recipes_bot.bot.CookingRecipesBot;


@Configuration
public class CookingRecipesBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(CookingRecipesBot cookingRecipesBot) throws TelegramApiException {
        var api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(cookingRecipesBot);
        return api;
    }
}
