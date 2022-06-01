package com.mihail.suppubot.bot.handlers;

import com.mihail.suppubot.bot.commands.commandTypes.IncidentCommandType;
import com.mihail.suppubot.bot.commands.impl.PhoneCommand;
import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.ChatStatus;
import com.mihail.suppubot.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CallbackHandler {

    private final ChatRepository chatRepository;

    @SneakyThrows
    public List<SendMessage> handle(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String[] param = callbackQuery.getData().split(":");

        Chat chat = chatRepository.findByChatId(callbackQuery.getMessage().getChatId());

        List<SendMessage> responses = new ArrayList<>();

        if (chat.getStatus() == ChatStatus.HAVE_AN_INCIDENT) {
            int incidentOrdinal = Integer.parseInt(param[1]);

            String response = IncidentCommandType.values()[incidentOrdinal].getAnswer();

            responses.add(SendMessage.builder()
                    .text(response)
                    .chatId(chat.getChatId().toString())
                    .build());

            responses.add(new PhoneCommand(
                    ChatStatus.MAKING_CALL_REQUEST_1_NUMBER,
                    """
                            Наші спеціалісти 🧑‍🔧 добре розуміються у подібних ситуація і можуть усунути проблему, що виникла у вас.
                            
                            Введіть свій номер телефону або натисніть кнопку \"Поділитися контактом\" і наш менеджер зателефонує вам для найбільш кваліфікаційного і швидкого вирішення вашої проблеми ♻️
                            """
            ).process(chat));
        }
        chatRepository.save(chat);
        return responses;
    }

}
