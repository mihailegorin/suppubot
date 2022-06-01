package com.mihail.suppubot.repository;

import com.mihail.suppubot.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Order getById(Integer id);

    Order findByReceiptNumber(String receiptNumber);

    List<Order> findAllByUserId(Integer userId);

}
