package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.bot.commands.commandTypes.IncidentCommandType;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncidentCommand implements ProcessableCommand {

    private final String responseMessageText;
    private final InlineKeyboardMarkup inlineKeyboard;

    @Override
    public SendMessage process(Chat chat) {
        chat.setStatus(ChatStatus.HAVE_AN_INCIDENT);

        return SendMessage.builder()
                .text(responseMessageText)
                .replyMarkup(inlineKeyboard)
                .chatId(chat.getChatId().toString())
                .build();
    }

    public IncidentCommand(Long chatId) {
        responseMessageText = "Що у вас трапилося?";

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        Arrays.stream(IncidentCommandType.values()).forEach(inc ->
                buttons.add(List.of(
                        InlineKeyboardButton.builder()
                                .text(inc.getName())
                                .callbackData("INCIDENT:" + inc.ordinal() + ":" + chatId.toString())
                                .build()))
        );
        inlineKeyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

}
