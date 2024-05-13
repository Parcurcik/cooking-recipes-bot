package ru.naumen.cookingrecipesbot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.domains.message.MessageToUser;
import ru.naumen.cookingrecipesbot.services.RecipeService;

import static ru.naumen.cookingrecipesbot.bot.Constants.HELP;
import static ru.naumen.cookingrecipesbot.bot.Constants.TEXT_START;

@Component
@RequiredArgsConstructor
public class BotMessageCreator {
    private final RecipeService recipeService;
    /**
     * Создается сообщение для пользователя с текстом приветствия и списком возможных команд бота.
     */
    public MessageToUser createMessageStartWorkBot(long chatId, String name) {
        String answer = "Привет, " + name + ", это телеграм бот рецептов тортов.\n" +
                TEXT_START;
        return new MessageToUser(chatId, answer);
    }

    /**
     * Создается сообщение для пользователя с текстом о доступных командах бота.
     */
    public MessageToUser createMessageAccessButtons(long chatId) {
        return new MessageToUser(chatId, HELP);
    }

    /**
     * Создается сообщение для пользователя с текстом о закрытии каталога товаров и параметром, указывающим на удаление кнопок
     */
    public MessageToUser createMessageDeleteButtons(long chatId) {
        String answer = "Вы закрыли каталог тортов";
        return new MessageToUser(chatId, answer);
    }

    /**
     * Создается сообщение для пользователя с ответом и кнопками в виде автозапчастей на выбранный автомобиль.
     */
    public MessageToUser createMessageNamesRecipes(long chatId) {
        String answer = "";
        int i = 1;
        for (Recipe recipe: recipeService.getAll()){
            answer += i + ")" + recipe.getName() + "\n";
            i++;
        }

        return new MessageToUser(chatId, answer);
    }

    /**
     * Создается сообщение для пользователя с текстом о недоступной команде.
     */
    public MessageToUser createMessageNotFoundCommand(long chatId) {
        String answer = "Команда не найдена";
        return new MessageToUser(chatId, answer);
    }
}
