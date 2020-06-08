package com.example.adoption.web.securityConfig;

import com.example.adoption.data.models.Center;
import com.example.adoption.data.services.CenterService;
import com.example.adoption.data.services.CenterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
CenterService centerService;

    public UserDetailsServiceImpl(CenterService centerService) {
        this.centerService = centerService;

    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Center> center = centerService.findByName(name);
        if(center == null) throw new UsernameNotFoundException("Center not found " + name);
        return new UserDetailsImpl(center.get());
    }



}
