package com.mihail.suppubot.controller.user;

import com.mihail.suppubot.dto.incomming_data.UserData;
import com.mihail.suppubot.dto.incomming_data.UserDataWithOrder;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.dto.responses.UserResponse;
import com.mihail.suppubot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation User API
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<UserResponse> createUser(UserData data) {
        return new ResponseEntity(service.createUser(data), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> createUserWithOrder(UserDataWithOrder data, Integer departmentId) {
        return new ResponseEntity(service.createUserWithOrder(data, departmentId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> getUsers() {
        return new ResponseEntity(service.getUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> getUser(Integer id) {
        return new ResponseEntity(service.getUser(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderResponse>> getUserOrders(Integer id) {
        return new ResponseEntity(service.getUserOrders(id), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Integer id, UserData data) {
        return new ResponseEntity(service.updateUser(id, data), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> deleteUser(Integer id) {
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
