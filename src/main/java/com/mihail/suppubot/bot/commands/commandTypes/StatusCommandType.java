package com.mihail.suppubot.bot.commands.commandTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum StatusCommandType {

    BY_PHONE("За номером телефона"),
    BY_RECEIPT("За номером квитанції");

    private final String name;

    public static Optional<StatusCommandType> parse(String name) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(name) || b.name.equalsIgnoreCase(name))
                .findFirst();
    }

}
