package ru.naumen.cookingrecipesbot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.domains.ShoppingList;
import ru.naumen.cookingrecipesbot.domains.User;
import ru.naumen.cookingrecipesbot.domains.message.*;
import ru.naumen.cookingrecipesbot.services.RecipeService;
import ru.naumen.cookingrecipesbot.services.ShoppingListService;
import ru.naumen.cookingrecipesbot.services.UserService;

import java.io.IOException;

import static ru.naumen.cookingrecipesbot.bot.Constants.*;

@Component
@RequiredArgsConstructor
public class BotLogic {


    private final BotMessageCreator botMessageCreator;
    private final RecipeService recipeService;
    private final ShoppingListService shoppingListService;
    private final UserService userService;

    /**
     * Точка входа, куда будут поступать сообщения от пользователей. Отсюда будет идти вся новая логика.
     *
     * @return
     */
    public MessageToUser onUpdateReceived(MessageFromUser messageFromUser) throws IOException {
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
            case COMMAND_RECIPES_LIST -> {
                return botMessageCreator.createMessageListRecipes(chatId);
            }
            default -> {
                for(Recipe recipe: recipeService.getAll()){
                    if (messageText.equals(recipe.getId().toString())){
                        userService.addUser(new User(chatId, messageFromUser.getUserName(), recipe.getId()));
                        return botMessageCreator.createMessageAboutCake(chatId, recipe.getId());
                    }
                }
                if ((messageText.split(" ")[0] + " " + messageText.split(" ")[1]).equals("Добавить рецепт")){
                    shoppingListService.addRecipe(new ShoppingList(chatId, messageFromUser.getUserName(),
                            Long.valueOf(messageFromUser.getMessage().split(" ")[2])));
                    return botMessageCreator.addRecipeInList(chatId);
                }
               return botMessageCreator.createMessageNotFoundCommand(chatId);
            }
        }
    }
}
