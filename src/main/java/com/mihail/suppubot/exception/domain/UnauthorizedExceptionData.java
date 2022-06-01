package com.mihail.suppubot.exception.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Not Found Exception")
public class UnauthorizedExceptionData {

    @ApiModelProperty(notes = "Unauthorized Exception Status", example = "401")
    private int status;
    @ApiModelProperty(notes = "Message", example = "Access denied")
    private String message;

}
