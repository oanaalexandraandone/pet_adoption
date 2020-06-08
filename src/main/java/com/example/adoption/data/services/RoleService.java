package com.example.adoption.data.services;

import com.example.adoption.data.models.Role;


public interface RoleService  extends CrudInterface<Role, Integer> {
    Role findByName(String name);
}
