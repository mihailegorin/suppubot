package com.mihail.suppubot.repository;

import com.mihail.suppubot.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

    Department findFirstByDistrict(String district);

}
