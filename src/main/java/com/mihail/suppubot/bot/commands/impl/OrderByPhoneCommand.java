package com.mihail.suppubot.bot.commands.impl;

import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class OrderByPhoneCommand extends PhoneCommand {

    @Override
    public SendMessage process(Chat chat) {
        return super.process(chat);
    }

    public OrderByPhoneCommand() {
        super(ChatStatus.GETTING_ORDER_STATUS_BY_PHONE);
    }
}
