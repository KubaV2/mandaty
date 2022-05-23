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
import javax.validation.constraints.Size;

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
    @Size(max = 100, message = "Maksymalna długość nazwy grupy to 100 znaków")
    private String optgroup;

    @NotBlank(message = "Opis wykroczenia nie może być pusty")
    @Size(max = 400, message = "Maksymalna długość opisu to 400 znaków")
    private String description;

}
