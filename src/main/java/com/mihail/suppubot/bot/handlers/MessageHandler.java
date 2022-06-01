package com.mihail.suppubot.bot.handlers;


import com.mihail.suppubot.bot.commands.commandTypes.HomeCommandType;
import com.mihail.suppubot.bot.commands.commandTypes.MainCommandType;
import com.mihail.suppubot.bot.commands.ProcessableCommand;
import com.mihail.suppubot.bot.commands.commandTypes.StatusCommandType;
import com.mihail.suppubot.bot.commands.impl.*;
import com.mihail.suppubot.dto.responses.DepartmentResponse;
import com.mihail.suppubot.entity.*;
import com.mihail.suppubot.repository.ChatRepository;
import com.mihail.suppubot.repository.DepartmentRepository;
import com.mihail.suppubot.repository.OrderRepository;
import com.mihail.suppubot.repository.UserRepository;
import com.mihail.suppubot.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MessageHandler {

    private static final String TELEGRAM_COMMAND_PREFIX = "/";
    private final DepartmentService departmentService;
    private final ChatRepository chatRepository;
    private final DepartmentRepository departmentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @SneakyThrows
    public List<SendMessage> handle(Update update) {
        Chat chat = provideChatRecord(update.getMessage().getChatId());
        String text = update.getMessage().getText();

        List<SendMessage> responses = tryProcessMainCommand(chat, text);
        if (responses.size() != 0)
            return responses;

        switch (chat.getStatus()) {
            case START -> {
                Optional<HomeCommandType> command = HomeCommandType.parse(text);
                if (command.isPresent())
                    responses = processCommand(command.get(), chat);
            }
            case GETTING_ORDER_STATUS -> {
                Optional<StatusCommandType> command = StatusCommandType.parse(text);
                if (command.isPresent())
                    responses = processCommand(command.get(), chat);
            }
            case GETTING_ORDERS -> responses =
                    processGetAllOrdersMessage(text, chat);
            case GETTING_CONTACTS -> responses =
                    processGetContactsMessage(text, chat);
            case GETTING_ORDER_STATUS_BY_PHONE, GETTING_ORDER_STATUS_BY_RECEIPT -> responses =
                    processGetActiveOrderMessage(text, chat);
            case MAKING_CALL_REQUEST_1_NUMBER, MAKING_CALL_REQUEST_2_NAME -> responses =
                    processMakeRequestMessage(text, chat);
        }
        chatRepository.save(chat);
        return responses;
    }

    private List<SendMessage> tryProcessMainCommand(Chat chat, String text) {
        List<SendMessage> responses = new ArrayList<>();
        if (isTelegramCommand(text)) {
            text = text.substring(TELEGRAM_COMMAND_PREFIX.length());
        }
        Optional<MainCommandType> mainCommand = MainCommandType.parse(text);
        if (mainCommand.isPresent()) {
            responses = processCommand(mainCommand.get(), chat);
            chatRepository.save(chat);
        }
        return responses;
    }

    private Chat provideChatRecord(Long chatId) {
        Chat chat = chatRepository.findByChatId(chatId);
        if (chat == null) {
            chat = Chat.builder()
                    .chatId(chatId)
                    .status(ChatStatus.START)
                    .build();
            chatRepository.save(chat);
        }
        return chat;
    }

    private boolean isTelegramCommand(String text) {
        return text.startsWith(TELEGRAM_COMMAND_PREFIX);
    }

    private List<SendMessage> processCommand(MainCommandType command, Chat chat) {
        ProcessableCommand processableCommand = null;
        switch (command) {
            case START -> processableCommand = new StartCommand();
            case HOME -> processableCommand = new HomeCommand();
        }
        assert processableCommand != null;
        return Collections.singletonList(processableCommand.process(chat));
    }

    private List<SendMessage> processCommand(HomeCommandType command, Chat chat) {
        ProcessableCommand processableCommand = null;
        switch (command) {
            case STATUS -> processableCommand = new StatusCommand();
            case CONTACTS -> {
                List<String> departmentRegions = departmentService.getDepartments().stream()
                        .map(DepartmentResponse::getDistrict)
                        .collect(Collectors.toList());
                processableCommand = new ContactsCommand(departmentRegions);
            }
            case ORDERS -> processableCommand = new PhoneCommand(ChatStatus.GETTING_ORDERS);
            case CALL_REQUEST -> processableCommand = new PhoneCommand(ChatStatus.MAKING_CALL_REQUEST_1_NUMBER);
            case INCIDENT -> processableCommand = new IncidentCommand(chat.getChatId());
        }
        assert processableCommand != null;
        return Collections.singletonList(processableCommand.process(chat));
    }

    private List<SendMessage> processCommand(StatusCommandType command, Chat chat) {
        ProcessableCommand processableCommand = null;
        switch (command) {
            case BY_PHONE -> processableCommand = new OrderByPhoneCommand();
            case BY_RECEIPT -> processableCommand = new OrderByReceiptCommand();
        }
        assert processableCommand != null;
        return Collections.singletonList(processableCommand.process(chat));
    }

    public List<SendMessage> processMakeRequestMessage(String messageText, Chat chat) {
        ProcessableCommand processableCommand = null;

        switch (chat.getStatus()) {
            case MAKING_CALL_REQUEST_1_NUMBER -> {
                /*
                    TODO создать запрос на внешний апи клиент для создания собітия о запросе звонка
                     или создать таблицу для таких запросов и клиента телеграмма которому будет отправляться запрос о звонке
                */
                processableCommand = new CallRequestCommand();
            }
            case MAKING_CALL_REQUEST_2_NAME -> {
                /*
                    TODO: добавление имени к запросу и его отправка
                 */
                processableCommand = new StartCommand("Наш менеджер невдовзі зателефонує вам");
            }
        }
        assert processableCommand != null;
        return Collections.singletonList(processableCommand.process(chat));
    }

    public List<SendMessage> processGetContactsMessage(String messageText, Chat chat) {
        Department department = departmentRepository.findFirstByDistrict(messageText);
        if (department == null) {
            return new ArrayList<>();
        }
        return Collections.singletonList(
                new StartCommand(
                        "Сервісний центр \"А-сервіс\" " + department.getDistrict() + "\n\n" +
                                "\uD83D\uDCCD м. " + department.getCity() + ", " + department.getAddress() + "\n" +
                                department.getDescription() + "\n\n" +
                                department.getWorkingHours() + "\n\n" +
                                "\uD83D\uDCF2 " + department.getPhone() + "\n\n" +
                                department.getGoogleMapsLink()
                ).process(chat)
        );
    }

    public List<SendMessage> processGetAllOrdersMessage(String messageText, Chat chat) {
        String message;
        User user = userRepository.findByPhoneNumber(messageText);
        if (user == null) {
            message = "За вказаним номером замовлень не знайдено";
        } else {
            List<Order> orders = orderRepository.findAllByUserId(user.getId());
            if (orders.size() == 0) {
                message = "За вказаним номером замовлень не знайдено";
            } else {
                List<SendMessage> responses = new ArrayList<>();
                orders.stream()
                        .map(this::getOrderMessage).toList()
                        .forEach(mes -> responses.add(new StartCommand(mes).process(chat)));
                return responses;
            }
        }
        return Collections.singletonList(new StartCommand(message).process(chat));
    }

    public List<SendMessage> processGetActiveOrderMessage(String messageText, Chat chat) {
        String message;
        if (chat.getStatus() == ChatStatus.GETTING_ORDER_STATUS_BY_PHONE) {
            User user = userRepository.findByPhoneNumber(messageText);
            if (user == null) {
                message = "За данним номером активних заказів не знайдено";
            } else {
                List<Order> orders = orderRepository.findAllByUserId(user.getId()).stream()
                        .filter(o -> o.getStatus() != OrderStatus.TAKEN).toList();
                if (orders.size() == 0) {
                    message = "За данним номером активних заказів не знайдено";
                } else {
                    List<SendMessage> responses = new ArrayList<>();
                    orders.stream()
                            .map(this::getOrderMessage).toList()
                            .forEach(mes -> responses.add(new StartCommand(mes).process(chat)));
                    return responses;
                }
            }
        } else {
            Order order = orderRepository.findByReceiptNumber(messageText);
            if (order == null)
                message = "Замовлення за данним номером квитанції не знайдено";
            else
                message = getOrderMessage(order);
        }
        return Collections.singletonList(new StartCommand(message).process(chat));
    }

    private String getOrderMessage(Order order) {
        return "Замовлення №" + order.getReceiptNumber() + "\n\n" +
                order.getDevice() + "\n\n" +
                "Звернення: " + order.getProblem() + "\n" +
                "Статус: " + order.getStatus().getName() + "\n" +
                "Вартість: " + order.getCost() + " грн." + "\n\n" +
                "У СЦ за адресою: \uD83D\uDCCD м. " + order.getDepartment().getCity() + ", " + order.getDepartment().getAddress() + "\n";
    }


}
