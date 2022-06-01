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
public class NotFoundExceptionData {

    @ApiModelProperty(notes = "Not Found Exception Status", example = "404")
    private int status;
    @ApiModelProperty(notes = "Message", example = "OBJECT_NOT_FOUND")
    private String message;

}
