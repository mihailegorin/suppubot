package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.commandTypes.MainCommandType;
import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class OrderByReceiptCommand implements ProcessableCommand {
    private final String responseMessageText;
    private final ReplyKeyboard keyboard;

    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.GETTING_ORDER_STATUS_BY_RECEIPT);
        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }

    public OrderByReceiptCommand() {
        responseMessageText = "Введіть номер квитанції";

        KeyboardRow row1 = new KeyboardRow();
        row1.add(MainCommandType.HOME.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .build();
    }
}
