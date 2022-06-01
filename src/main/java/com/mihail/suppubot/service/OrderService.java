package com.mihail.suppubot.service;

import com.mihail.suppubot.dto.incomming_data.OrderData;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.entity.Department;
import com.mihail.suppubot.entity.Order;
import com.mihail.suppubot.entity.OrderStatus;
import com.mihail.suppubot.entity.User;
import com.mihail.suppubot.exception.BadRequestException;
import com.mihail.suppubot.exception.ConflictException;
import com.mihail.suppubot.exception.NotFoundException;
import com.mihail.suppubot.repository.DepartmentRepository;
import com.mihail.suppubot.repository.OrderRepository;
import com.mihail.suppubot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public OrderResponse createOrder(Integer userId, Integer departmentId, OrderData data) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Order order = new Order();
        order.setStatus(OrderStatus.RECEIVED);
        order.setDepartment(department);
        order.setUser(user);
        order.setDevice(data.getDevice());
        order.setProblem(data.getProblem());
        order.setReceiptNumber(data.getReceiptNumber());
        order.setReceiptDate(data.getReceiptDate());
        order.setCost(data.getCost());

        orderRepository.save(order);

        return new OrderResponse(order);
    }

    public List<OrderResponse> getOrders() {
        List<Order> response = (List<Order>) orderRepository.findAll();
        return response.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        return new OrderResponse(order);
    }

    public OrderResponse updateOrder(Integer id, OrderData data) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        order.setDevice(data.getDevice() != null ? data.getDevice() : order.getDevice());
        order.setProblem(data.getProblem() != null ? data.getProblem() : order.getProblem());
        order.setReceiptNumber(data.getReceiptNumber() != null ? data.getReceiptNumber() : order.getReceiptNumber());
        order.setCost(data.getCost() != null ? data.getCost() : order.getCost());
        order.setCost(data.getCost() != null ? data.getCost() : order.getCost());
        orderRepository.save(order);

        return new OrderResponse(order);
    }

    public OrderResponse updateOrderStatus(Integer id, OrderStatus status, Integer warranty) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        switch (status) {
            case RECEIVED -> throw new ConflictException("Order is already received");
            case DONE -> {
                order.setCompletionDate(123D);
            }
            case TAKEN -> {
                if (warranty == null) throw new BadRequestException("Warranty is required");
                order.setTakeawayDate(123d);
                order.setWarrantyExpDate(123+3d);
            }
        }

        order.setStatus(status);
        orderRepository.save(order);

        return new OrderResponse(order);
    }


    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        orderRepository.delete(order);
    }

}
