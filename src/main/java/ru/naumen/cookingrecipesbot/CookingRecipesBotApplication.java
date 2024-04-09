package ru.naumen.cookingrecipesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.naumen.cookingrecipesbot.bots.TelegramBot;

@SpringBootApplication
public class CookingRecipesBotApplication {

	public static void main(String[] args) throws TelegramApiException {
		TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
		bot.registerBot(new TelegramBot());
		SpringApplication.run(CookingRecipesBotApplication.class, args);
	}

}
