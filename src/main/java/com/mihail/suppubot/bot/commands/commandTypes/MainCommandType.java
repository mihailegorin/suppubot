package com.mihail.suppubot.bot.commands.commandTypes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum MainCommandType {

    START("start"),
    HOME("Назад ↩️");

    private final String name;

    public static Optional<MainCommandType> parse(String name) {
        return Arrays.stream(values())
                .filter(b -> b.name().equalsIgnoreCase(name) || b.name.equalsIgnoreCase(name))
                .findFirst();
    }

}
