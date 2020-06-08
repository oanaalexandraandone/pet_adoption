package com.example.adoption.data.services;


import com.example.adoption.data.models.Pet;

import java.util.List;

public interface PetService extends CrudInterface <Pet, Integer> {
    List<Pet> findAllByPetType(String petType);

}
