package com.mihail.suppubot.controller.department;

import com.mihail.suppubot.dto.incomming_data.DepartmentData;
import com.mihail.suppubot.dto.responses.DepartmentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Departments Operations.
 */
@Validated
@Api(tags = "Department")
public interface DepartmentApi {

    @ApiOperation(
            value = "Create new department", nickname = "createDepartment",
            notes = "This API creates new department object.",
            response = DepartmentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created.", response = DepartmentResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.POST,
            value = "api/departments",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentData data);


    @ApiOperation(
            value = "Get department list", nickname = "getDepartments",
            notes = "This API returns department list.",
            response = DepartmentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DepartmentResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/departments",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DepartmentResponse> getDepartments();


    @ApiOperation(
            value = "Get department", nickname = "getDepartment",
            notes = "This API returns department.",
            response = DepartmentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DepartmentResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/departments/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DepartmentResponse> getDepartment(
            @PathVariable Integer id
    );


    @ApiOperation(
            value = "Update department", nickname = "updateDepartment",
            notes = "This API updates department.",
            response = DepartmentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DepartmentResponse.class),
            @ApiResponse(code = 400, message = "Bad Request.", response = Error.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "api/departments/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Integer id,
            @RequestBody DepartmentData data
    );


    @ApiOperation(
            value = "Delete department", nickname = "deleteDepartment",
            notes = "This API delete department.",
            response = DepartmentResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DepartmentResponse.class),
            @ApiResponse(code = 404, message = "Not found.", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = Error.class)
    })
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "api/departments/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<DepartmentResponse> deleteDepartment(
            @PathVariable Integer id
    );

}
