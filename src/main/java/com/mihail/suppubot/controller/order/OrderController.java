package com.mihail.suppubot.controller.order;

import com.mihail.suppubot.dto.incomming_data.OrderData;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.entity.OrderStatus;
import com.mihail.suppubot.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation Order API
 */
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService service;

    @Override
    public ResponseEntity<OrderResponse> createOrder(Integer userId, Integer departmentId, OrderData data) {
        return new ResponseEntity(service.createOrder(userId, departmentId, data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<OrderResponse> getOrders() {
        return new ResponseEntity(service.getOrders(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(Integer id) {
        return new ResponseEntity(service.getOrder(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderResponse> updateOrder(Integer id, OrderData data) {
        return new ResponseEntity(service.updateOrder(id, data), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderResponse> updateOrderStatus(Integer id, OrderStatus status, Integer warranty) {
        return new ResponseEntity(service.updateOrderStatus(id, status, warranty), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderResponse> deleteOrder(Integer id) {
        service.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
