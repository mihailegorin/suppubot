package com.mihail.suppubot.controller.user;

import com.mihail.suppubot.dto.incomming_data.UserData;
import com.mihail.suppubot.dto.incomming_data.UserDataWithOrder;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.dto.responses.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Users Operations.
 */
@Validated
@Api(tags = "User")
public interface UserApi {

    @ApiOperation(
            value = "Create new User", nickname = "createUser",
            notes = "This API creates new User object.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> createUser(@RequestBody UserData data);


    @ApiOperation(
            value = "Create new User with Order", nickname = "createUserWithOrder",
            notes = "This API creates new User with Order.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created.", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "api/users/withOrder",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> createUserWithOrder(
            @RequestBody UserDataWithOrder data,
            @RequestParam Integer departmentId
    );


    @ApiOperation(
            value = "Get User list", nickname = "getUsers",
            notes = "This API returns User list.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> getUsers();


    @ApiOperation(
            value = "Get User", nickname = "getUser",
            notes = "This API returns User.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> getUser(
            @PathVariable Integer id
    );


    @ApiOperation(
            value = "Get User Orders", nickname = "getUserOrders",
            notes = "This API returns User.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/users/{id}/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<OrderResponse>> getUserOrders(
            @PathVariable Integer id
    );


    @ApiOperation(
            value = "Update User", nickname = "updateUser",
            notes = "This API updates User.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserData data
    );


    @ApiOperation(
            value = "Delete User", nickname = "deleteUser",
            notes = "This API delete User.",
            response = UserResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> deleteUser(
            @PathVariable Integer id
    );

}
