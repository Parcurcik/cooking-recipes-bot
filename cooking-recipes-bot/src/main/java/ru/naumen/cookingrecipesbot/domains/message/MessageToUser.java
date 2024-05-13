package ru.naumen.cookingrecipesbot.domains.message;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageToUser {

    @Getter
    private final long chatId;
    @Getter
    private final String text;
    @Getter
    private final Boolean inlineKeyboardMarkup;

    public MessageToUser(long chatId, String text, Boolean inlineKeyboardMarkup) {
        this.chatId = chatId;
        this.text = text;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

}
