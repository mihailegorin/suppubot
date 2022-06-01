package com.mihail.suppubot.bot.commands.commandTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum HomeCommandType {

    ORDERS("Мої замовлення \uD83D\uDCD1"),
    STATUS("Статус замовлення \uD83D\uDD0D"),
    CONTACTS("Контакти ℹ️"),
    CALL_REQUEST("Викликати майстра \uD83E\uDDD1\u200D\uD83D\uDD27"),
    INCIDENT("Экстренна ситуація \uD83D\uDEA8");

    private final String name;

    public static Optional<HomeCommandType> parse(String name) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(name) || b.name.equalsIgnoreCase(name))
                .findFirst();
    }

}
