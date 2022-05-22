package com.pirko.mandaty.model;

import com.pirko.mandaty.validation.IsName;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PESEL(message = "Niepoprawny numer PESEL")
    private String pesel;

    @IsName(message = "Poprawny format imienia to pierwsza duża litera oraz reszta małych (max 45 znaków)")
    private String firstName;

    @IsName(message = "Poprawny format nazwiska to pierwsza duża litera oraz reszta małych (max 45 znaków)")
    private String lastName;

    @Email(message = "Wprowadź poprawny adres email np. example@wp.pl")
    @Length(min = 1, max = 50, message = "Maksymalna długość adresu e-mail to 50 znaków.")
    private String email;

    @Min(value = 0, message = "Minimalna ilość punktów dla osoby to 0")
    @NotNull(message = "Ilośc punktów nie może być pusta")
    private Integer points;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private List<Mandate> mandates;


}
