package ru.naumen.cookingrecipesbot.domains.message;

public class MessageToUser {
    public MessageToUser(long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    private final long chatId;
    private final String text;

    /**
     * Получение id чата
     *
     * @return Id текущего чата
     */
    public long getChatId() {
        return chatId;
    }

    /**
     * Получение текста сообщения
     *
     * @return Текст сообщения
     */
    public String getText() {
        return text;
    }

}
