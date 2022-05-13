package com.pirko.mandaty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.pl.PESEL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mandate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PESEL(message = "Niepoprawny numer PESEL")
    private String pesel;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent
    private LocalDateTime dateTime = LocalDateTime.now();
    @Size(min = 1, max = 100, message = "Maksymalna długość nazwy wykroczenia to 100 znaków")
    private String offense;
    @Max(value = 15, message = "Maksymalna ilość punktów dla wykroczenia to 15")
    @Min(value = 1, message = "Minimalna ilość punktów dla wykroczenia to 1")
    private Integer points;
    @DecimalMax(value = "5000", message = "Maksymalna kwota mandatu to 5000 pln")
    @DecimalMin(value = "1", message = "Minimalna kwota mandatu to 1 pln")
    private BigDecimal amount;

}
