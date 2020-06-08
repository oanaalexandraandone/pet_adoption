package com.example.adoption.data.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "pets")
public class Pet extends BaseEntity{
@Column(name= "name")
    private String name;

@ManyToOne
@JoinColumn (name = "Type")
    private PetType petType;

@Column( name = "dateOfBirth")
@DateTimeFormat(pattern = "yyyy-MM-dd")
private LocalDate dob;

@Column(name= "description")
private String description;


@JoinColumn(name = "center_id")
@ManyToOne(fetch=FetchType.LAZY)
    private Center center;

@Lob
private Byte [] image;

public void setDob( int year, int month, int day){
    this.dob= LocalDate.of(year, month, day);
}


public String getPetTypeName(){
    return this.petType.name;
}


}
