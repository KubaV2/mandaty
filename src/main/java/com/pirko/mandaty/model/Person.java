package com.pirko.mandaty.model;

import com.pirko.mandaty.validation.IsName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PESEL(message = "Niepoprawny numer PESEL")
    private String pesel;
    @IsName
    private String firstName;
    @IsName
    private String lastName;
    @Email(message = "Wprowadź poprawny adres email np. example@wp.pl")
    private String email;
    @OneToMany
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private List<Mandate> mandates;

}
