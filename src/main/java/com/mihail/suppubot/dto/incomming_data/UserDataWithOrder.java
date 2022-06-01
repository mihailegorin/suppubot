package com.mihail.suppubot.dto.incomming_data;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDataWithOrder {

    private String name;
    private String phoneNumber;
    private OrderData orderData;

}
