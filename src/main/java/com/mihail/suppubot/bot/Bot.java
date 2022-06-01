package com.mihail.suppubot.bot;

import com.mihail.suppubot.bot.handlers.CallbackHandler;
import com.mihail.suppubot.bot.handlers.ContactHandler;
import com.mihail.suppubot.bot.handlers.MessageHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String username;
    @Value("${bot.token}")
    private String token;

    @Autowired
    private CallbackHandler callbackHandler;
    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private ContactHandler contactHandler;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        List<SendMessage> responses = new ArrayList<>();

        if (update.hasCallbackQuery()) {
            responses = callbackHandler.handle(update);
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            responses = messageHandler.handle(update);
        } else if (update.getMessage().hasContact()) {
            responses = contactHandler.handle(update);
        }

        for (SendMessage res : responses) execute(res);
    }

}
