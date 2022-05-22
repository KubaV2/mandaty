package com.pirko.mandaty.controller;

import com.pirko.mandaty.common.ResponseEntityCreator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIT {

    @LocalServerPort
    private int serverPort;

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/osoba");
    }

    @Test
    void shouldReturn3xxStatusWhenAddPersonSuccessfully() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);
        //then
        assertTrue(response.getStatusCode().is3xxRedirection());
    }

    @Test
    void shouldReturn4xxStatusWhenAddPersonIdIsInDB() throws Exception {
        //when
        Long id = 1L;
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                String.valueOf(id), "02250752599", "Jakub",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "{\"message\":\"Osoba ID " + id + " już istnieje!\"}";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusWhenAddPersonPeselIsInDB() throws Exception {
        //when
        String pesel = "94051913613";
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson("94051913613", "Jakub",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "{\"message\":\"Osoba numerze pesel " + pesel + " już istnieje!\"}";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsWrong() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "1234567891234", "Jakub",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Niepoprawny numer PESEL\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsEmpty() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "", "Jakub",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Niepoprawny numer PESEL\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenFirstNameIsWrong() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "jakub123",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Poprawny format imienia to pierwsza duża litera oraz reszta małych (max 45 znaków)\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenFirstNameIsEmpty() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "",
                "Nowak", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Poprawny format imienia to pierwsza duża litera oraz reszta małych (max 45 znaków)\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenLastNameIsWrong() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "nowak123", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Poprawny format nazwiska to pierwsza duża litera oraz reszta małych (max 45 znaków)\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenLastNameIsEmpty() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "", "jnowak@wp.pl", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Poprawny format nazwiska to pierwsza duża litera oraz reszta małych (max 45 znaków)\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenEmailAdressIsWrong() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "Nowak", "jnowak123", "0", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Wprowadź poprawny adres email np. example@wp.pl\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPointsIsUnderZero() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "Nowak", "jnowak@wp.pl",
                "-1", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Minimalna ilość punktów dla osoby to 0\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPointsIsEmpty() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForPerson(
                "02250752599", "Jakub",
                "Nowak", "jnowak@wp.pl",
                "", createServerAddress() + "/dodaj", HttpMethod.POST);

        String message = "[\"Ilośc punktów nie może być pusta\"]";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

}
