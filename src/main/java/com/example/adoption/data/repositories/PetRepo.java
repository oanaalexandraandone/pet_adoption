package com.example.adoption.data.repositories;


import com.example.adoption.data.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepo extends CrudRepository<Pet, Integer> {

    List<Pet> findAllByPetType(String petType);
}
