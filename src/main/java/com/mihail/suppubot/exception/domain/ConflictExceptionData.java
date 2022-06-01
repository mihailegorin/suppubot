package com.mihail.suppubot.exception.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Conflict Exception")
public class ConflictExceptionData  {

    @ApiModelProperty(notes = "Conflict Exception Status", example = "409")
    private int status;
    @ApiModelProperty(notes = "Message", example = "CONFLICT_EXCEPTION")
    private String message;


}
