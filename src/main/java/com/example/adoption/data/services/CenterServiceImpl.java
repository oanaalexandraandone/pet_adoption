package com.example.adoption.data.services;


import com.example.adoption.data.models.Center;
import com.example.adoption.data.repositories.CenterRepo;
import com.example.adoption.web.errors.EmailAlreadyExistsException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CenterServiceImpl implements CenterService {
    CenterRepo centerRepo;
    BCryptPasswordEncoder encoder;


    public CenterServiceImpl(CenterRepo centerRepo, @Lazy BCryptPasswordEncoder encoder){
        this.centerRepo=centerRepo;
        this.encoder=encoder;
    }

    @Override
    public Center save(Center object) {
        if(emailExists(object.getEmail())){
            throw new EmailAlreadyExistsException("Email already used.");
        }
        return centerRepo.save(object);
    }


    @Override
    public void deleteById(Integer id) {
        centerRepo.deleteById(id);

    }

    @Override
    public void deleteAll(List<Center> list) {
        centerRepo.deleteAll(list);

    }

    @Override
    public Optional<Center> findByID(Integer id) {
        return centerRepo.findById(id);
    }

    @Override
    public Set<Center> findAll() {
        Set<Center> centers = new HashSet<>();
        centerRepo.findAll().forEach(centers::add);
        return centers;
    }

    @Override
    public List<Center> findAllByNameLike(String name) {
        return centerRepo.findAllByNameLike(name);
    }

    @Override
    public List<Center> findAllByCity(String city) {
        return centerRepo.findAllByCity(city);
    }

    @Override
    public Optional<Center> findByName(String name) {
        return centerRepo.findByName(name);
    }

    @Override
    public Optional<Center> findByEmail(String email) {
        return centerRepo.findByEmail(email);
    }

    @Override
    public boolean emailExists(String email) {
        if(centerRepo.findByEmail(email).isPresent())
        return true;
        return false;
    }

    @Override
    public boolean newPasswordAlreadyUsed( String newPassword, String oldPassword) {
        if(encoder.matches(newPassword, oldPassword))
            return true;
        return false;
    }

    @Override
    public boolean checkOldPassword(final Center center, final String oldPassword) {
        return encoder.matches(oldPassword, center.getPassword());
    }


    @Override
    public void changePassword(Center center, String newPass) {
        center.setPassword(newPass);
        centerRepo.save(center);
    }
}
