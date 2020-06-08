package com.example.adoption.data.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CrudInterface<T, Integer> {

    T save (T object);
    void deleteById(Integer id);
    void deleteAll (List<T> list);
    Optional<T> findByID(Integer id);
    Set<T> findAll();

}
