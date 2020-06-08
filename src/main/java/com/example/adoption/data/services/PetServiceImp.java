package com.example.adoption.data.services;


import com.example.adoption.data.models.Pet;
import com.example.adoption.data.repositories.PetRepo;
import com.example.adoption.data.repositories.PetTypeRepo;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PetServiceImp implements PetService {
    PetRepo petRepo;
    PetTypeRepo petTypeRepo;

    public PetServiceImp(PetRepo petRepo, PetTypeRepo petTypeRepo) {
        this.petRepo = petRepo;
        this.petTypeRepo = petTypeRepo;
    }

    @Override
    public Pet save(Pet object) {
        return petRepo.save(object);
    }

    @Override
    public void deleteById(Integer id) {
        petRepo.deleteById(id);

    }

    @Override
    public void deleteAll(List<Pet> list) {
        petRepo.deleteAll(list);
    }

    @Override
    public Optional<Pet> findByID(Integer id) {
        return petRepo.findById(id);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepo.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public List<Pet> findAllByPetType(String petType) {
        return petRepo.findAllByPetType(petType);
    }
}
