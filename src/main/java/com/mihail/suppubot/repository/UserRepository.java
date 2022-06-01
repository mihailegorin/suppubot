package com.mihail.suppubot.repository;

import com.mihail.suppubot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByPhoneNumber(String phone);

}
