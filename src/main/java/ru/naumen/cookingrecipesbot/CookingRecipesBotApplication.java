package ru.naumen.cookingrecipesbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class CookingRecipesBotApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(CookingRecipesBotApplication.class, args);
	}

}
