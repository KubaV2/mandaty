package com.pirko.mandaty.validation;

import com.pirko.mandaty.model.Offense;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OffenseValidationIT {

    @Autowired
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    void shouldReturnExceptionWhenOffenseOptGroupIsTooLong() {
        //given
        Offense offense = new Offense(1L, stringRepeat(105), "Abcdefgh");
        //when
        Set<ConstraintViolation<Offense>> violations = validator.validate(offense);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Maksymalna długość nazwy grupy to 100 znaków");
    }

    @Test
    void shouldReturnExceptionWhenOffenseOptGroupIsEmpty() {
        //given
        Offense offense = new Offense(1L, "", "Abcdefgh");
        //when
        Set<ConstraintViolation<Offense>> violations = validator.validate(offense);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Nazwa grupy wykroczenia nie może być pusta");
    }

    @Test
    void shouldReturnExceptionWhenOffenseDescriptionIsTooLong() {
        //given
        Offense offense = new Offense(1L, "abc", stringRepeat(405));
        //when
        Set<ConstraintViolation<Offense>> violations = validator.validate(offense);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Maksymalna długość opisu to 400 znaków");
    }

    @Test
    void shouldReturnExceptionWhenOffenseDescriptionIsEmpty() {
        //given
        Offense offense = new Offense(1L, "abc", "");
        //when
        Set<ConstraintViolation<Offense>> violations = validator.validate(offense);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Opis wykroczenia nie może być pusty");
    }

    private String stringRepeat(int number) {
        return "a" + "abc".repeat(Math.max(0, number));
    }

}
