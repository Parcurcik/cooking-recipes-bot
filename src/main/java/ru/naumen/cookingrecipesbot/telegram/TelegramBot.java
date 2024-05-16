package ru.naumen.cookingrecipesbot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.naumen.cookingrecipesbot.bot.Bot;
import ru.naumen.cookingrecipesbot.bot.BotConfig;
import ru.naumen.cookingrecipesbot.bot.BotLogic;
import ru.naumen.cookingrecipesbot.domains.Recipe;
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
        if (update.hasCallbackQuery()) {
            MessageFromUser message = new MessageFromUser(
                    update.getCallbackQuery().getMessage().getChatId(),
                    update.getCallbackQuery().getData(),
                    update.getCallbackQuery().getFrom().getUserName()
            );
            MessageToUser messageToUser = null;
            try {
                messageToUser = botLogic.onUpdateReceived(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (messageToUser == null) {
                return;
            }
            this.sendMessage(messageToUser);
        }
        else {
            MessageFromUser message = new MessageFromUser(
                    update.getMessage().getChatId(),
                    update.getMessage().getText(),
                    update.getMessage().getChat().getFirstName()
            );
            MessageToUser messageToUser = null;
            try {
                messageToUser = botLogic.onUpdateReceived(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (messageToUser == null) {
                return;
            }
            this.sendMessage(messageToUser);
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

    @Override
    public void sendMessage(MessageToUser messageToUser) {
        try {
            execute(convertMessageToUserToSendMessage(messageToUser));
        } catch (TelegramApiException e) {
            throw new RuntimeException("Не удалось отправить сообщение. "
                    + e.getMessage(), e);
        }
    }

    private InlineKeyboardMarkup getKeyBoardAllCakes() {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        for (Recipe recipe: recipeService.getAll()) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            InlineKeyboardButton newButton = new InlineKeyboardButton();
            newButton.setText(recipe.getName());
            newButton.setCallbackData(recipe.getId().toString());
            row.add(newButton);
            keyboardRows.add(row);
        }
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    private InlineKeyboardMarkup AddButton(Long recipeId){
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardRows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton newButton = new InlineKeyboardButton();
        newButton.setText("Добавить рецепт");
        newButton.setCallbackData("Добавить рецепт " + recipeId.toString());
        row.add(newButton);
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }

    private SendMessage convertMessageToUserToSendMessage(MessageToUser messageToUser) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(messageToUser.getChatId());
        if (messageToUser.getText() != null) sendMessage.setText(messageToUser.getText());
        if (messageToUser.getInlineKeyboardMarkup().equals("recipes")) sendMessage.setReplyMarkup(getKeyBoardAllCakes());
        if (messageToUser.getInlineKeyboardMarkup().equals("add")) sendMessage.setReplyMarkup(AddButton(messageToUser.getCallBackRecipeId()));
        return sendMessage;
    }

}
