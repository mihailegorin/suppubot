package com.mihail.suppubot.service;

import com.mihail.suppubot.dto.incomming_data.DepartmentData;
import com.mihail.suppubot.dto.responses.DepartmentResponse;
import com.mihail.suppubot.entity.Department;
import com.mihail.suppubot.exception.NotFoundException;
import com.mihail.suppubot.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentResponse createDepartment(DepartmentData data) {
        Department department = new Department();
        department.setCity(data.getCity());
        department.setDistrict(data.getDistrict());
        department.setAddress(data.getAddress());
        department.setPhone(data.getPhone());
        department.setWorkingHours(data.getWorkingHours());
        department.setDescription(data.getDescription());
        department.setGoogleMapsLink(data.getGoogleMapsLink());

        departmentRepository.save(department);

        return new DepartmentResponse(department);
    }

    public List<DepartmentResponse> getDepartments() {
        List<Department> response = (List<Department>) departmentRepository.findAll();
        return response.stream().map(DepartmentResponse::new).collect(Collectors.toList());
    }

    public DepartmentResponse getDepartment(Integer id) {
        Department response = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        return new DepartmentResponse(response);
    }

    public DepartmentResponse updateDepartment(Integer id, DepartmentData data) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found"));

        department.setCity(data.getCity() != null ? data.getCity() : department.getCity());
        department.setDistrict(data.getDistrict() != null ? data.getDistrict() : department.getDistrict());
        department.setAddress(data.getAddress() != null ? data.getAddress() : department.getAddress());
        department.setPhone(data.getPhone() != null ? data.getPhone() : department.getPhone());
        department.setWorkingHours(data.getWorkingHours() != null ? data.getWorkingHours() : department.getWorkingHours());
        department.setDescription(data.getDescription() != null ? data.getDescription() : department.getDescription());
        department.setGoogleMapsLink(data.getGoogleMapsLink() != null ? data.getGoogleMapsLink() : department.getGoogleMapsLink());
        departmentRepository.save(department);

        return new DepartmentResponse(department);
    }

    public void deleteDepartment(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department not found"));
        departmentRepository.delete(department);
    }


}
