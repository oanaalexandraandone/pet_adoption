package com.example.adoption.data.services;

import com.example.adoption.data.repositories.RoleRepo;
import com.example.adoption.data.models.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    RoleRepo roleRepo;

    public RoleServiceImp(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role save(Role object) {
        return roleRepo.save(object);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepo.deleteById(id);

    }

    @Override
    public void deleteAll(List<Role> list) {
        roleRepo.deleteAll(list);
    }

    @Override
    public Optional<Role> findByID(Integer id) {
        return roleRepo.findById(id);
    }

    @Override
    public Set<Role> findAll() {
        Set<Role> roles= new HashSet<>();
        roleRepo.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }
}
