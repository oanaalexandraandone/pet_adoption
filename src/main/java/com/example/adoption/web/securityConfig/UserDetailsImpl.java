package com.example.adoption.web.securityConfig;

import com.example.adoption.data.models.Center;
import com.example.adoption.data.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {


    public UserDetailsImpl(Center center) {
        this.name = center.getName();
        this.password=center.getPassword();
        this.active=center.isActive();
        authorities= new ArrayList<>();
        for (Role role: center.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getName()));
        }
    }


    private String name;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public boolean getIsActive(){
        return active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
