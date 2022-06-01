package com.mihail.suppubot.dto.incomming_data;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    private String device;
    private String problem;
    private String receiptNumber;
    private Double receiptDate;
    private Double cost;

}
