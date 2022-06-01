package com.mihail.suppubot.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    RECEIVED("Прийнято ⭕"),
    IN_PROGRESS("У процесі \uD83C\uDF00"),
    DONE("Виконано. Можна забирати ✅"),
    TAKEN("Видано ☑️");

    private final String name;
}

