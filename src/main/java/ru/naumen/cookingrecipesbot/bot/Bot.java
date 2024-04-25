package ru.naumen.cookingrecipesbot.bot;

import ru.naumen.cookingrecipesbot.domains.message.MessageToUser;

/**
 * Интерфейс бота
 */
public interface Bot {
    /**
     * Выводит сообщение
     * @param message класс сообщения
     */
    void sendMessage(MessageToUser message);
}

