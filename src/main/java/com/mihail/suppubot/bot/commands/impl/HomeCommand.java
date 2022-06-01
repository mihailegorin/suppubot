package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.entity.Chat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class HomeCommand extends StartCommand {

    @Override
    public SendMessage process(Chat chat) {
        return super.process(chat);
    }

    public HomeCommand() {
        super("Чим ще я можу допомогти вам?");
    }

}
