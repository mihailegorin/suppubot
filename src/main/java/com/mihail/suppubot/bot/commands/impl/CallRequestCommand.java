package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.bot.commands.commandTypes.MainCommandType;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class CallRequestCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final ReplyKeyboard keyboard;

    public CallRequestCommand() {
        responseMessageText = "Як ваше ім'я?";

        KeyboardRow row1 = new KeyboardRow();
        row1.add(MainCommandType.HOME.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .build();
    }

    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.MAKING_CALL_REQUEST_2_NAME);
        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }


}
