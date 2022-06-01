package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ContactsCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final ReplyKeyboard keyboard;

    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.GETTING_CONTACTS);
        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(keyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }

    public ContactsCommand(List<String> departmentRegions) {
        responseMessageText = "Оберіть район сервісного центру";

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        for (int i = 0; i < departmentRegions.size(); i++) {
            KeyboardRow row = new KeyboardRow();
            int rowSeparator = i+2;
            for (int j = i; j < rowSeparator; j++) {
                row.add(departmentRegions.get(j));
                i = j;
            }
            keyboardRows.add(row);
        }

        keyboard = new ReplyKeyboardMarkup(keyboardRows);
    }

}
