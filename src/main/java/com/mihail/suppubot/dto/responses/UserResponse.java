package com.mihail.suppubot.dto.responses;

import com.mihail.suppubot.entity.Chat;
import com.mihail.suppubot.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private Chat chat;
    private String name;
    private String phoneNumber;
    private List<OrderResponse> orders;

    public UserResponse(User user) {
        this.id = user.getId();
        this.chat = user.getChat();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.orders = user.getOrders().stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }
}
