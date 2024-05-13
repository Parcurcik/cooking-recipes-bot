package ru.naumen.cookingrecipesbot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.domains.message.*;
import ru.naumen.cookingrecipesbot.services.RecipeService;

import static ru.naumen.cookingrecipesbot.bot.Constants.*;

@Component
@RequiredArgsConstructor
public class BotLogic {

    private final BotMessageCreator botMessageCreator;
    private final RecipeService recipeService;

    /**
     * Точка входа, куда будут поступать сообщения от пользователей. Отсюда будет идти вся новая логика.
     *
     * @return
     */
    public MessageToUser onUpdateReceived(MessageFromUser messageFromUser) {
        if (messageFromUser.getMessage() == null || messageFromUser.getMessage().isEmpty()) {
            return null;
        }
        String messageText = messageFromUser.getMessage();
        long chatId = messageFromUser.getChatId();
        switch (messageText) {
            case COMMAND_START -> {
                return botMessageCreator.createMessageStartWorkBot(chatId, messageFromUser.getUserName());
            }
            case COMMAND_RECIPES -> {
                return botMessageCreator.createMessageNamesRecipes(chatId);
            }
            case COMMAND_EXIT -> {
                return botMessageCreator.createMessageDeleteButtons(chatId);
            }
            case COMMAND_HELP -> {
                return botMessageCreator.createMessageAccessButtons(chatId);
            }
            default -> {
                for(Recipe recipe: recipeService.getAll()){
                    if (messageText.equals(recipe.getId().toString())){
                        return botMessageCreator.createMessageAboutCake(chatId, recipe.getId());
                    }
                }
               return botMessageCreator.createMessageNotFoundCommand(chatId);
            }
        }
    }
}
