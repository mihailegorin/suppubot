package com.mihail.suppubot.controller.order;

import com.mihail.suppubot.dto.incomming_data.OrderData;
import com.mihail.suppubot.dto.responses.OrderResponse;
import com.mihail.suppubot.entity.OrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Orders Operations.
 */
@Validated
@Api(tags = "Order")
public interface OrderApi {

    @ApiOperation(
            value = "Create new order", nickname = "createOrder",
            notes = "This API creates new Order object.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created.", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "api/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> createOrder(
            @RequestParam Integer userId,
            @RequestParam Integer departmentId,
            @RequestBody OrderData data
    );


    @ApiOperation(
            value = "Get Order list", nickname = "getOrders",
            notes = "This API returns Order list.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/orders",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> getOrders();


    @ApiOperation(
            value = "Get Order", nickname = "getOrder",
            notes = "This API returns Order.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/orders/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> getOrder(
            @PathVariable Integer id
    );


    @ApiOperation(
            value = "Update Order", nickname = "updateOrder",
            notes = "This API updates Order.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/orders/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> updateOrder(
            @PathVariable Integer id,
            @RequestBody OrderData data
    );


    @ApiOperation(
            value = "Update Order Status", nickname = "updateOrderStatus",
            notes = "This API updates Order status.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/orders/{id}/status",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Integer id,
            @RequestParam OrderStatus status,
            @RequestParam(required = false) Integer warranty
    );


    @ApiOperation(
            value = "Delete Order", nickname = "deleteOrder",
            notes = "This API delete Order.",
            response = OrderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = OrderResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "api/orders/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<OrderResponse> deleteOrder(
            @PathVariable Integer id
    );

}
