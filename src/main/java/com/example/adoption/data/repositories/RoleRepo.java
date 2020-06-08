package com.example.adoption.data.repositories;

import com.example.adoption.data.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
