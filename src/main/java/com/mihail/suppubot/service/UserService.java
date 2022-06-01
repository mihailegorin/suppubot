package com.mihail.suppubot.service;

import com.mihail.suppubot.dto.incomming_data.UserData;
import com.mihail.suppubot.dto.incomming_data.UserDataWithOrder;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.dto.responses.UserResponse;
import com.mihail.suppubot.entity.Department;
import com.mihail.suppubot.entity.User;
import com.mihail.suppubot.exception.NotFoundException;
import com.mihail.suppubot.repository.DepartmentRepository;
import com.mihail.suppubot.repository.OrderRepository;
import com.mihail.suppubot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public UserResponse createUser(UserData data) {
        User user = new User();
        user.setName(data.getName());
        user.setPhoneNumber(data.getPhoneNumber());
        user.setOrders(new ArrayList<>());

        userRepository.save(user);

        return new UserResponse(user);
    }

    public UserResponse createUserWithOrder(UserDataWithOrder data, Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        User user = new User();
        user.setName(data.getName());
        user.setPhoneNumber(data.getPhoneNumber());
        userRepository.save(user);

        OrderResponse orderResponse = orderService.createOrder(user.getId(), departmentId, data.getOrderData());

        user.setOrders(new ArrayList<>());
        user.getOrders().add(orderRepository.getById(orderResponse.getId()));
        userRepository.save(user);

        return new UserResponse(user);
    }

    public List<UserResponse> getUsers() {
        List<User> response = (List<User>) userRepository.findAll();
        return response.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public UserResponse getUser(Integer id) {
        User response = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserResponse(response);
    }

    public List<OrderResponse> getUserOrders(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return user.getOrders().stream().map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(Integer id, UserData data) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setName(data.getName() != null ? data.getName() : user.getName());
        user.setPhoneNumber(data.getPhoneNumber() != null ? data.getPhoneNumber() : user.getPhoneNumber());
        userRepository.save(user);

        return new UserResponse(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

}
