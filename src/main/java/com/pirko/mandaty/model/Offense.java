package com.pirko.mandaty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Offense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nazwa grupy wykroczenia nie może być pusta")
    private String optgroup;
    @NotBlank(message = "Opis wykroczenia nie może być pusty")
    private String description;

    public Offense(String offense) {
        this.description = offense;
    }

}
