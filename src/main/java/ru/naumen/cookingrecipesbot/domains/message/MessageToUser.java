package ru.naumen.cookingrecipesbot.domains.message;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageToUser {

    @Getter
    private final long chatId;
    @Getter
    private final String text;
    @Getter
    private final String inlineKeyboardMarkup;
    @Getter
    private final Long callBackRecipeId;

    public MessageToUser(long chatId, String text, String inlineKeyboardMarkup) {
        this.chatId = chatId;
        this.text = text;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
        this.callBackRecipeId = null;
    }

    public MessageToUser(long chatId, String text, String inlineKeyboardMarkup, Long callBackRecipeId) {
        this.chatId = chatId;
        this.text = text;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
        this.callBackRecipeId = callBackRecipeId;
    }

}
