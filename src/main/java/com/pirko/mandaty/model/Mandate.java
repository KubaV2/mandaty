package com.pirko.mandaty.model;

import lombok.*;
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
@ToString
public class Mandate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PESEL(message = "Niepoprawny numer PESEL")
    private String pesel;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent(message = "Mandat nie może mieć przyszłej daty wystawienia")
    @NotNull(message = "Data nie może być pusta")
    private LocalDateTime dateTime = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Offense> offenses;

    @Max(value = 15, message = "Maksymalna ilość punktów dla wykroczenia to 15")
    @Min(value = 0, message = "Minimalna ilość punktów dla wykroczenia to 0")
    @NotNull(message = "Ilośc punktów nie może być pusta")
    private Integer points;

    @DecimalMax(value = "5000", message = "Maksymalna kwota mandatu to 5000 pln")
    @DecimalMin(value = "0", message = "Minimalna kwota mandatu to 0 pln")
    @NotNull(message = "Kwota nie może być pusta")
    private BigDecimal amount;

}
