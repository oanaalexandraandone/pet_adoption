package com.example.adoption.data.repositories;

import com.example.adoption.data.models.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepo extends CrudRepository<PetType, Integer> {
}
