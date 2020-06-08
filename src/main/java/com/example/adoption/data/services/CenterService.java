package com.example.adoption.data.services;


import com.example.adoption.data.models.Center;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface CenterService extends CrudInterface<Center, Integer> {
    List<Center> findAllByNameLike(String name);
    List<Center> findAllByCity(String city);
    Optional<Center> findByName(String name);
    Optional <Center> findByEmail(String email);
    boolean emailExists(String email);
    boolean newPasswordAlreadyUsed(String newPassword, String oldPassword);
    boolean checkOldPassword(Center center, String oldPassword);
    void changePassword(Center center, String newPass);

}
