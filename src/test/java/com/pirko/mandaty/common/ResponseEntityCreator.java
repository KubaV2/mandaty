package com.pirko.mandaty.common;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ResponseEntityCreator {

    public static ResponseEntity<String> createForDelete(String url, HttpMethod httpMethod) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return testRestTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);
    }

    public static ResponseEntity<String> createForMandate(String pesel, String dateTime, String offenses,
                                                          String points, String amount, String url, HttpMethod httpMethod) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("pesel", pesel);
        map.add("dateTime", dateTime);
        map.add("offenses", offenses);
        map.add("points", points);
        map.add("amount", amount);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return testRestTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);
    }

    public static ResponseEntity<String> createForMandate(String id, String pesel, String dateTime, String offenses,
                                                          String points, String amount, String url, HttpMethod httpMethod) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", id);
        map.add("pesel", pesel);
        map.add("dateTime", dateTime);
        map.add("offenses", offenses);
        map.add("points", points);
        map.add("amount", amount);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return testRestTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);
    }

    public static ResponseEntity<String> createForPerson(String pesel, String firstName, String lastName,
                                                         String email, String points, String url, HttpMethod httpMethod) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("pesel", pesel);
        map.add("firstName", firstName);
        map.add("lastName", lastName);
        map.add("email", email);
        map.add("points", points);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return testRestTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);
    }

    public static ResponseEntity<String> createForPerson(String id, String pesel, String firstName, String lastName,
                                                          String email, String points, String url, HttpMethod httpMethod) {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", id);
        map.add("pesel", pesel);
        map.add("firstName", firstName);
        map.add("lastName", lastName);
        map.add("email", email);
        map.add("points", points);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        return testRestTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);
    }

}
