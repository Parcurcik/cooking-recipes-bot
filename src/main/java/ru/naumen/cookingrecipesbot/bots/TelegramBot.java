package ru.naumen.cookingrecipesbot.bots;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.cookingrecipesbot.config.BotConfig;
import ru.naumen.cookingrecipesbot.controllers.CakesController;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final CakesController cakesController = new CakesController();

    private static final BotConfig botConfig = new BotConfig();
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chat_id = update.getMessage().getChatId().toString();

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);
            message.setText("cakesController.getRecipes()");
            cakesController.getRecipes();
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
