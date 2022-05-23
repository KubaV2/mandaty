package com.pirko.mandaty.validation;

import com.pirko.mandaty.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonValidationIT {

    @Autowired
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        this.validator = vf.getValidator();
    }

    @Test
    void shouldReturnExceptionWhenPersonPeselIsWrong() {
        //given
        Person person = new Person(1L, "123", "Jan", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Niepoprawny numer PESEL");
    }

    @Test
    void shouldReturnExceptionWhenPersonFirstNameIsWrong() {
        //given
        Person person = new Person(1L, "94051913613", "jan123", "Nowak", "nowak@wp.pl", 0, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Poprawny format imienia to pierwsza duża litera oraz reszta małych (max 45 znaków)");
    }

    @Test
    void shouldReturnExceptionWhenPersonLastNameIsWrong() {
        //given
        Person person = new Person(1L, "94051913613", "Jan", "nowak321", "nowak@wp.pl", 0, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Poprawny format nazwiska to pierwsza duża litera oraz reszta małych (max 45 znaków)");
    }

    @Test
    void shouldReturnExceptionWhenPersonEmailAdressIsWrong() {
        //given
        Person person = new Person(1L, "94051913613", "Jan", "Nowak", "nowak", 0, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Wprowadź poprawny adres email np. example@wp.pl");
    }

    @Test
    void shouldReturnExceptionWhenPersonEmailAdressIsToLong() {
        //given
        Person person = new Person(1L, "94051913613", "Jan", "Nowak",
                "nowaknowanowanowanoawnowanoawnoawnaownawawawadadewdsa", 0, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Maksymalna długość adresu e-mail to 50 znaków.");
    }

    @Test
    void shouldReturnExceptionWhenPersonPointsIsUnderZero() {
        //given
        Person person = new Person(1L, "94051913613", "Jan", "Nowak",
                "nowak@wp.pl", -1, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Minimalna ilość punktów dla osoby to 0");
    }

    @Test
    void shouldReturnExceptionWhenPersonPointsIsEmpty() {
        //given
        Person person = new Person(1L, "94051913613", "Jan", "Nowak",
                "nowak@wp.pl", null, new ArrayList<>());
        //when
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0), "Ilośc punktów nie może być pusta");
    }


}