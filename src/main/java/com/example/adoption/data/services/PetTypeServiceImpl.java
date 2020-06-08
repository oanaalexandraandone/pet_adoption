package com.example.adoption.data.services;


import com.example.adoption.data.models.PetType;
import com.example.adoption.data.repositories.PetTypeRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class PetTypeServiceImpl implements PetTypeService {

    PetTypeRepo petTypeRepo;

    public PetTypeServiceImpl(PetTypeRepo petTypeRepo){
        this.petTypeRepo = petTypeRepo;
    }


    @Override
    public PetType save(PetType object) {
        return petTypeRepo.save(object);
    }

    @Override
    public void deleteById(Integer id) {
        petTypeRepo.deleteById(id);
    }

    @Override
    public void deleteAll(List<PetType> list) {
        petTypeRepo.deleteAll(list);

    }

    @Override
    public Optional<PetType> findByID(Integer id) {
        return petTypeRepo.findById(id);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> animalTypes= new HashSet<>();
        petTypeRepo.findAll().forEach(animalTypes::add);
        return animalTypes;
    }

}
