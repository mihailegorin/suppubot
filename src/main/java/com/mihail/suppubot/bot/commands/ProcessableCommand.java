package com.mihail.suppubot.bot.commands;

import com.mihail.suppubot.entity.Chat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ProcessableCommand {
    SendMessage process(Chat chat);
}
