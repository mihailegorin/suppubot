package com.mihail.suppubot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RestError {
    private int status;
    private String message;
}
