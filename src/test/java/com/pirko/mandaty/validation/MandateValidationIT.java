package com.pirko.mandaty.validation;


import com.pirko.mandaty.controller.MandateController;
import com.pirko.mandaty.exception.PersonExistException;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Person;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseExtractor;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
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
    void shouldReturn4xxWhenMandatePeselIsEmpty() {
        //given
        Mandate mandate = new Mandate(1L, "", LocalDateTime.now(), new ArrayList<>(), 10, BigDecimal.valueOf(100));
        //when
        Set<ConstraintViolation<Mandate>> violations = validator.validate(mandate);
        List<String> messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        //then
        assertEquals(messages.get(0),"Numer PESEL nie może być pusty");
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
        assertEquals(messages.get(0),"Niepoprawny numer PESEL");
    }

}
