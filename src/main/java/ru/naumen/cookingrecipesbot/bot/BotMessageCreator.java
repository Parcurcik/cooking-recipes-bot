package ru.naumen.cookingrecipesbot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.domains.message.MessageToUser;
import ru.naumen.cookingrecipesbot.services.RecipeService;
import ru.naumen.cookingrecipesbot.services.ShoppingListService;
import ru.naumen.cookingrecipesbot.services.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static ru.naumen.cookingrecipesbot.bot.Constants.HELP;
import static ru.naumen.cookingrecipesbot.bot.Constants.TEXT_START;

@Component
@RequiredArgsConstructor
public class BotMessageCreator {
    private final RecipeService recipeService;
    private final ShoppingListService shoppingListService;
    private final UserService userService;
    /**
     * Создается сообщение для пользователя с текстом приветствия и списком возможных команд бота.
     */
    public MessageToUser createMessageStartWorkBot(long chatId, String name) {
        String answer = "Привет, " + name + ", это телеграм бот рецептов тортов.\n" +
                TEXT_START;
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Создается сообщение для пользователя с текстом о доступных командах бота.
     */
    public MessageToUser createMessageAccessButtons(long chatId) {
        return new MessageToUser(chatId, HELP,"false");
    }

    /**
     * Создается сообщение для пользователя с текстом о закрытии каталога товаров и параметром, указывающим на удаление кнопок
     */
    public MessageToUser createMessageDeleteButtons(long chatId) throws IOException {
        String answer = "Вы закрыли каталог тортов";
        userService.removeUser(chatId);
        shoppingListService.removeUserRecipes(chatId);
        return new MessageToUser(chatId, answer,"false");
    }

    /**
     * Создается сообщение для пользователя, в котором выводятся названия тортов в виде кнопок
     */
    public MessageToUser createMessageNamesRecipes(long chatId) {
        String answer = "Список тортов:";
        return new MessageToUser(chatId, answer, "recipes");
    }

    /**
     * Создается сообщение для пользователя с текстом о недоступной команде.
     */
    public MessageToUser createMessageNotFoundCommand(long chatId) {
        String answer = "Команда не найдена";
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Создается сообщение для пользователя с текстом о торте,а также создается кнопка "Добавить"
     * @param chatId
     * @param recipeId
     * @return
     */
    public MessageToUser createMessageAboutCake(long chatId, long recipeId) {
        Recipe recipe = recipeService.getRecipeById(recipeId).get();
        String answer = "Имя: " + recipe.getName() + "\n"
                + "Описание: " +  new String(recipe.getDescription(), StandardCharsets.UTF_8) + "\n"
                + "Ингридиенты: " + new String(recipe.getIngredients(), StandardCharsets.UTF_8);
        return new MessageToUser(chatId, answer, "add", recipeId);
    }

    /**
     * Выводится сообщение и добавляется торт
     * @param chatId
     * @return
     */
    public MessageToUser addRecipeInList(long chatId) {
        String answer = "Рецепт добавлен";
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Выводится сообщение и очищается список добавленных тортов
     * @param chatId
     * @return
     * @throws IOException
     */
    public MessageToUser deleteListRecipes(long chatId) throws IOException {
        shoppingListService.clearList();
        String answer = "Список добавленных товаров очищен";
        return new MessageToUser(chatId, answer, "false");
    }

    /**
     * Создается сообщение со списком добавленных тортов
     * @param chatId
     * @return
     * @throws IOException
     */
    public MessageToUser createMessageListRecipes(Long chatId) throws IOException {
        ArrayList<Long> recipeIds = shoppingListService.getAddNumbersRecipes(chatId);
        String answer = "";
        for (Long recipeId : recipeIds) {
            Recipe recipe = recipeService.getRecipeById(recipeId).get();
            answer += "Имя: " + recipe.getName() + "\n"
                    + "Описание: " +  new String(recipe.getDescription(), StandardCharsets.UTF_8) + "\n"
                    + "Ингридиенты: " + new String(recipe.getIngredients(), StandardCharsets.UTF_8) + "\n"
                    + "\n";
        }
        if (answer.equals(""))
            answer = "Ваш список товаров пуст";
        return new MessageToUser(chatId, answer, "false");
    }
}
