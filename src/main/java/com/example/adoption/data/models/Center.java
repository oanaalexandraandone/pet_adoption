package com.example.adoption.data.models;

import com.example.adoption.web.securityConfig.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Null;
import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class Center extends BaseEntity{

    public Center(){
        this.isActive=true;
    }

    public Center(UserDetailsImpl userDetails){
        this.name=userDetails.getUsername();
        this.password=userDetails.getPassword();
        this.isActive=userDetails.getIsActive();
        this.roles= Arrays.asList(new Role("center"));
    }



    @Column(name = "name")
    private String name;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name= "pets")
    private Set<Pet> pets = new HashSet<>();

    private boolean isActive;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "center_role",
            joinColumns = @JoinColumn(name = "center_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    private String password;
    private String confirmPassword;
    private String testPass;
    @Email
    protected String email;
    @Column(name="telephone")
    protected String telephone;

    public void setPassword(String password){
       this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }



}
