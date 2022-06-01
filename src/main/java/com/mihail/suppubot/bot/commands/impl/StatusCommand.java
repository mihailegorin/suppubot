package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.commandTypes.MainCommandType;
import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.bot.commands.commandTypes.StatusCommandType;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class StatusCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final ReplyKeyboard keyboard;

    public StatusCommand() {
        responseMessageText = "Яким їз способів мені знайти ваше замовлення?";

        KeyboardRow row1 = new KeyboardRow();
        row1.add(StatusCommandType.BY_PHONE.getName());
        row1.add(StatusCommandType.BY_RECEIPT.getName());

        KeyboardRow row2 = new KeyboardRow();
        row2.add(MainCommandType.HOME.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .keyboardRow(row2)
                .build();
    }


    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.GETTING_ORDER_STATUS);
        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }



}
