package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.commandTypes.HomeCommandType;
import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Getter
@Setter
public class StartCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final ReplyKeyboard keyboard;

    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.START);

        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }

    public StartCommand() {
        responseMessageText = "Вітаю! Чим можу допомогти?";

        KeyboardRow row1 = new KeyboardRow();
        row1.add(HomeCommandType.STATUS.getName());
        row1.add(HomeCommandType.ORDERS.getName());

        KeyboardRow row2 = new KeyboardRow();
        row2.add(HomeCommandType.CONTACTS.getName());
        row2.add(HomeCommandType.CALL_REQUEST.getName());

        KeyboardRow row3 = new KeyboardRow();
        row3.add(HomeCommandType.INCIDENT.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .keyboardRow(row2)
                .keyboardRow(row3)
                .build();
    }

    public StartCommand(String responseMessageText) {
        this.responseMessageText = responseMessageText;

        KeyboardRow row1 = new KeyboardRow();
        row1.add(HomeCommandType.STATUS.getName());
        row1.add(HomeCommandType.ORDERS.getName());

        KeyboardRow row2 = new KeyboardRow();
        row2.add(HomeCommandType.CONTACTS.getName());
        row2.add(HomeCommandType.CALL_REQUEST.getName());

        KeyboardRow row3 = new KeyboardRow();
        row3.add(HomeCommandType.INCIDENT.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .keyboardRow(row2)
                .keyboardRow(row3)
                .build();
    }

}
