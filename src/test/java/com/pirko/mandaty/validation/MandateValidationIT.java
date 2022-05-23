package com.pirko.mandaty.validation;

import com.pirko.mandaty.model.Mandate;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MandateValidationIT {

    @Autowired
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    void shouldReturnExceptionWhenMandatePeselIsWrong() {
        //given
        Mandate mandate = new Mandate(1L, "1234", LocalDateTime.now(), new ArrayList<>(), 10, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Niepoprawny numer PESEL");
    }

    @Test
    void shouldReturnExceptionWhenMandateDateIsFromFuture() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now().plusDays(1), new ArrayList<>(), 10, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Mandat nie może mieć przyszłej daty wystawienia");
    }

    @Test
    void shouldReturnExceptionWhenMandateDateIsEmpty() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", null, new ArrayList<>(), 10, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Data nie może być pusta");
    }

    @Test
    void shouldReturnExceptionWhenPointsIsAboveFifteen() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), 16, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Maksymalna ilość punktów dla wykroczenia to 15");
    }

    @Test
    void shouldReturnExceptionWhenPointsIsUnderZero() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), -1, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Minimalna ilość punktów dla wykroczenia to 0");
    }

    @Test
    void shouldReturnExceptionWhenPointsIsEmpty() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), null, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Ilośc punktów nie może być pusta");
    }

    @Test
    void shouldReturnExceptionWhenAmountIsUnderZero() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), 10, BigDecimal.valueOf(-100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Minimalna kwota mandatu to 0 pln");
    }

    @Test
    void shouldReturnExceptionWhenAmountIsAboveFiveThousand() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), 10, BigDecimal.valueOf(5100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Maksymalna kwota mandatu to 5000 pln");
    }

    @Test
    void shouldReturnExceptionWhenAmountIsEmpty() {
        //given
        Mandate mandate = new Mandate(1L, "94051913613", LocalDateTime.now(), new ArrayList<>(), 10, null);
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Kwota nie może być pusta");
    }


}