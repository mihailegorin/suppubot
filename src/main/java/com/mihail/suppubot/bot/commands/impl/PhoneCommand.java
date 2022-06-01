package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.bot.commands.commandTypes.MainCommandType;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class PhoneCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final ReplyKeyboard keyboard;
    private final ChatStatus chatStatus;

    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(chatStatus);
        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }

    public PhoneCommand(ChatStatus chatStatus) {
        this.chatStatus = chatStatus;
        responseMessageText = "Введіть свій номер телефону або натисніть кнопку \"Поділитися контактом'\"";

        KeyboardButton button = new KeyboardButton("Поділитися контактом");
        button.setRequestContact(true);

        KeyboardRow row1 = new KeyboardRow();
        row1.add(button);
        row1.add(MainCommandType.HOME.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .build();
    }

    public PhoneCommand(ChatStatus chatStatus, String messageText) {
        this.chatStatus = chatStatus;
        responseMessageText = messageText;

        KeyboardButton button = new KeyboardButton("Поділитися контактом");
        button.setRequestContact(true);
        KeyboardRow row1 = new KeyboardRow();

        row1.add(button);
        row1.add(MainCommandType.HOME.getName());

        keyboard = ReplyKeyboardMarkup.builder()
                .keyboardRow(row1)
                .build();
    }
}
