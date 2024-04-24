package ru.naumen.cookingrecipesbot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.cookingrecipesbot.bot.Bot;
import ru.naumen.cookingrecipesbot.bot.BotConfig;
import ru.naumen.cookingrecipesbot.bot.BotLogic;
import ru.naumen.cookingrecipesbot.domains.message.MessageFromUser;
import ru.naumen.cookingrecipesbot.domains.message.MessageToUser;
import ru.naumen.cookingrecipesbot.services.RecipeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot implements Bot{

    private final BotConfig botConfig;
    private final BotLogic botLogic;
    private final RecipeService recipeService;

    public TelegramBot(BotConfig botConfig, BotLogic botLogic, RecipeService recipeService) {
        this.botConfig = botConfig;
        this.botLogic = botLogic;
        this.recipeService = recipeService;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Это телеграмм бот рецептов."));
        listOfCommands.add(new BotCommand("/recipes", "Перейти в каталог рецептов."));
        listOfCommands.add(new BotCommand("/recipes_list", "Вывести список добавленнх рецептов"));
        listOfCommands.add(new BotCommand("/delete", "Удалить из корзины выбранный рецепт"));
        listOfCommands.add(new BotCommand("/exit", "Выйти из каталога рецептов"));
        listOfCommands.add(new BotCommand("/help", "Справка"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        if (recipeService.getAll().isEmpty()) {
            try {
                recipeService.saveAllRecipesFromPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        MessageFromUser message = new MessageFromUser(
                update.getMessage().getChatId(),
                update.getMessage().getText(),
                update.getMessage().getChat().getFirstName()
        );
        MessageToUser messageToUser = botLogic.onUpdateReceived(message);
        if (messageToUser == null) {
            return;
        }
        this.sendMessage(messageToUser);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBOT_NAME();
    }
    @Override
    public String getBotToken() {
        return botConfig.getBOT_TOKEN();
    }

    @Override
    public void sendMessage(MessageToUser messageToUser) {
        try {
            execute(convertMessageToUserToSendMessage(messageToUser));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось отправить сообщение. "
                    + e.getMessage(), e);
        }
    }

    private SendMessage convertMessageToUserToSendMessage(MessageToUser messageToUser) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(messageToUser.getChatId());
        if (messageToUser.getText() != null) sendMessage.setText(messageToUser.getText());
        return sendMessage;
    }

}
