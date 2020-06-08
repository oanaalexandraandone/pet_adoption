package com.example.adoption.data.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "petTypes")
public class PetType extends BaseEntity{

    @Column(name="name")
    String name;
}
