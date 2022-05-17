package com.pirko.mandaty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mandate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Podaj poprawny numer PESEL")
    @PESEL(message = "Niepoprawny numer PESEL")
    private String pesel;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent
    private LocalDateTime dateTime = LocalDateTime.now();
    @OneToMany
    private List<Offense> offenses;
    @Max(value = 15, message = "Maksymalna ilość punktów dla wykroczenia to 15")
    @Min(value = 0, message = "Minimalna ilość punktów dla wykroczenia to 1")
    @NotNull(message = "Podaj poprawną ilość punktów")
    private Integer points;
    @DecimalMax(value = "5000", message = "Maksymalna kwota mandatu to 5000 pln")
    @DecimalMin(value = "0", message = "Minimalna kwota mandatu to 1 pln")
    @NotNull(message = "Podaj poprawną kwotę")
    private BigDecimal amount;

}
