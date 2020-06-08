package com.example.adoption.data.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "roles")
public class Role extends BaseEntity {


    public Role(String name){
        this.name=name;
    }

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Center> centers;

}
