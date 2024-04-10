package ru.naumen.cookingrecipesbot.bots;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.cookingrecipesbot.config.BotConfig;
import ru.naumen.cookingrecipesbot.repositories.RecipeRepository;
import ru.naumen.cookingrecipesbot.services.ParserPageService;
import ru.naumen.cookingrecipesbot.services.RecipeService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final RecipeService recipeService;

    private BotConfig botConfig = new BotConfig();
//    private boolean containsInBD = false;
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chat_id = update.getMessage().getChatId().toString();

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            if (recipeService.recipeRepository.findAll().isEmpty()){
                try {
                    recipeService.saveAllRecipesFromPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            message.setText("Какой торт вы бы хотели?");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBOT_NAME();
    }
    @Override
    public String getBotToken() {
        return botConfig.getBOT_TOKEN();
    }
}
