package com.mihail.suppubot.bot.handlers;

import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ContactHandler {

    private final ChatRepository chatRepository;
    private final MessageHandler messageHandler;
    public static final int NUMBER_PREFIX_LENGTH = 2;

    @SneakyThrows
    public List<SendMessage> handle(Update update) {
        Contact contact = update.getMessage().getContact();
        Chat chat = chatRepository.findByChatId(update.getMessage().getChatId());
        List<SendMessage> responses = new ArrayList<>();
        switch (chat.getStatus()) {
            case GETTING_ORDER_STATUS_BY_PHONE -> responses = messageHandler.processGetActiveOrderMessage(
                    contact.getPhoneNumber().substring(NUMBER_PREFIX_LENGTH), chat
            );
            case GETTING_ORDERS -> responses = messageHandler.processGetAllOrdersMessage(
                    contact.getPhoneNumber().substring(NUMBER_PREFIX_LENGTH), chat
            );
            case MAKING_CALL_REQUEST_1_NUMBER -> responses = messageHandler.processMakeRequestMessage(
                    contact.getPhoneNumber().substring(NUMBER_PREFIX_LENGTH), chat
            );
        }
        chatRepository.save(chat);
        return responses;

    }
}
