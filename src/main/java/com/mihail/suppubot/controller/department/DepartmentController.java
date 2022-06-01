package com.mihail.suppubot.controller.department;

import com.mihail.suppubot.dto.incomming_data.DepartmentData;
import com.mihail.suppubot.dto.responses.DepartmentResponse;
import com.mihail.suppubot.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Implementation Department API
 */

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi{

    private final DepartmentService service;

    @Override
    public ResponseEntity<DepartmentResponse> createDepartment(DepartmentData data) {
        return new ResponseEntity(service.createDepartment(data),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DepartmentResponse> getDepartments() {
        return new ResponseEntity(service.getDepartments(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentResponse> getDepartment(Integer id) {
        return new ResponseEntity(service.getDepartment(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentResponse> updateDepartment(Integer id, DepartmentData data) {
        return new ResponseEntity(service.updateDepartment(id, data), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentResponse> deleteDepartment(Integer id) {
        service.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
