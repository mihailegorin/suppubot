package com.mihail.suppubot.dto.responses;

import com.mihail.suppubot.entity.Order;
import com.mihail.suppubot.entity.OrderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Integer id;
    private OrderStatus status;
    private String device;
    private String problem;
    private Double cost;
    private String receiptNumber;
    private Double receiptDate;
    private Double completionDate;
    private Double takeawayDate;
    private Double warrantyExpDate;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.device = order.getDevice();
        this.problem = order.getProblem();
        this.cost = order.getCost();
        this.receiptNumber = order.getReceiptNumber();
        this.receiptDate = order.getReceiptDate();
        this.completionDate = order.getCompletionDate();
        this.takeawayDate = order.getTakeawayDate();
        this.warrantyExpDate = order.getWarrantyExpDate();
    }
}
