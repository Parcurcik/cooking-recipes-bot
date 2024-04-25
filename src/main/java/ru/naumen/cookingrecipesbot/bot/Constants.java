package ru.naumen.cookingrecipesbot.bot;

public class Constants {
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_EXIT = "/exit";
    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_RECIPES = "/recipes";
    public static final String HELP = """
            Справка о доступных командах:
            /recipes
            /recipes_list
            /history
            /delete
            /exit
            /help""";
    public static final String TEXT_START = """
            Доступны следующие команды:
            /recipes – Перейти в каталог тортов.
            /recipes_list - Вывести список добавленнх рецептов.
            /delete - Удалить все содержимое списка рецептов.
            /exit - Выйти из каталога тортов.
            /help - Справка.
            """;
}
