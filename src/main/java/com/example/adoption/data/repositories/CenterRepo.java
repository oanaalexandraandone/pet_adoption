package com.example.adoption.data.repositories;

import com.example.adoption.data.models.Center;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CenterRepo extends CrudRepository<Center, Integer> {

  List<Center> findAllByNameLike(String name);

  List<Center> findAllByCity(String city);

  Optional<Center> findByName(String name);
  Optional<Center> findByEmail(String email);

}
