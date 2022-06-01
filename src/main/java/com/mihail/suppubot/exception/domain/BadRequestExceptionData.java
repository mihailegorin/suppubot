package com.mihail.suppubot.exception.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Bad Request Exception")
public class BadRequestExceptionData {

    @ApiModelProperty(notes = "Bad Request Exception Status", example = "405")
    private int status;
    @ApiModelProperty(notes = "Message", example = "BAD_REQUEST_EXCEPTION")
    private String message;
}
