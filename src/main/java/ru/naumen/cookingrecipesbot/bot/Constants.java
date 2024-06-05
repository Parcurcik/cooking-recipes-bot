package ru.naumen.cookingrecipesbot.bot;

public class Constants {
    public static final String COMMAND_START = "/start";
    public static final String COMMAND_EXIT = "/exit";
    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_RECIPES = "/recipes";
    public static final String COMMAND_RECIPES_LIST = "/recipes_list";
    public static final String COMMAND_DELETE = "/delete";
    public static final String HELP = """
            Справка о доступных командах:
            /recipes - открывается каталог тортов и их описание
            /recipes_list - открывается список добавленных тортов
            /delete - очищается список добавленных тортов
            /exit - очищается корзина, а также данные пользователя""";
    public static final String TEXT_START = """
            Доступны следующие команды:
            /recipes – Перейти в каталог тортов.
            /recipes_list - Вывести список добавленнх рецептов.
            /delete - Удалить все содержимое списка рецептов.
            /exit - Выйти из каталога тортов.
            /help - Справка.
            """;
}
