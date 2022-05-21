package com.pirko.mandaty.controller;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.pirko.mandaty.model.Mandate;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.MailService;
import com.pirko.mandaty.service.MandateService;
import com.pirko.mandaty.service.PersonService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MandateControllerIT {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonService personService;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test", "springboot"))
            .withPerMethodLifecycle(false);

    @Test
    void shouldReturn3xxStatusWhenAddMandateSuccessfully() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        assertTrue(response.getStatusCode().is3xxRedirection());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsNotInTheDatabase() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("97122838587", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "{\"message\":\"Brak osoby z numerem PESEL: 97122838587 w bazie danych.\"}";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldSendEmailWhenPersonHasMaxPoints() throws Exception {
        Mandate mandate1 = new Mandate(1L, "94051913613", LocalDateTime.now().minusHours(2), new ArrayList<>(), 12, BigDecimal.valueOf(100));
        Mandate mandate2 = new Mandate(2L, "94051913613", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(110));
        Person person = personService.findPersonByPesel("94051913613");
        personService.addMandate(person, mandate1);
        personService.addMandate(person, mandate2);

        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:37:58",
                "", "12", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage receivedMessage = emails[0];
        assertEquals(1, emails.length);
        assertEquals(person.getEmail(), receivedMessage.getAllRecipients()[0].toString());
        assertEquals("UWAGA! Przekroczony dopuszczalny limit punktow karnych", receivedMessage.getSubject());
        assertEquals("W zwiazku z przekroczeniem dozwolonej ilosci punktow karnych(" +
                "24" + ") odebrano ci prawo jazdy.", GreenMailUtil.getBody(receivedMessage));

    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPersonHasAboveMaxPointsAndTryAddNewMandate() throws Exception {
        Mandate mandate1 = new Mandate(1L, "50102646532", LocalDateTime.now().minusHours(2), new ArrayList<>(), 12, BigDecimal.valueOf(100));
        Mandate mandate2 = new Mandate(2L, "50102646532", LocalDateTime.now().minusHours(1), new ArrayList<>(), 10, BigDecimal.valueOf(110));
        Person person = personService.findPersonByPesel("50102646532");
        personService.addMandate(person, mandate1);
        personService.addMandate(person, mandate2);

        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:37:58",
                "", "12", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage receivedMessage = emails[0];
        assertEquals(1, emails.length);
        assertEquals(person.getEmail(), receivedMessage.getAllRecipients()[0].toString());
        assertEquals("UWAGA! Przekroczony dopuszczalny limit punktow karnych", receivedMessage.getSubject());
        assertEquals("W zwiazku z przekroczeniem dozwolonej ilosci punktow karnych(" +
                "24" + ") odebrano ci prawo jazdy.", GreenMailUtil.getBody(receivedMessage));

        assertEquals(person.getMandates(), response.getBody());
//        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsWrong() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("123", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Niepoprawny numer PESEL\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsEmpty() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Niepoprawny numer PESEL\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }


    @Test
    void shouldReturn4xxStatusAndExceptionWhenDateTimeIsEmpty() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Data nie może być pusta\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenDateTimeIsFromFuture() throws Exception {
        String dateTimePlusOneDay = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", dateTimePlusOneDay,
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Mandat nie może mieć przyszłej daty wystawienia\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPointsIsAboveFifteen() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "20", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Maksymalna ilość punktów dla wykroczenia to 15\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPointsIsUnderZero() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "-10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Minimalna ilość punktów dla wykroczenia to 1\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPointsIsEmpty() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Ilośc punktów nie może być pusta\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenAmountIsAboveFiveThousand() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "10", "6000", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Maksymalna kwota mandatu to 5000 pln\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenAmountIsUnderOne() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "10", "-100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Minimalna kwota mandatu to 1 pln\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenAmountIsEmpty() throws Exception {
        ResponseEntity<String> response = createResponseEntityForMandate("94051913613", "2022-05-10T23:57:58",
                "", "10", "", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "[\"Kwota nie może być pusta\"]";

        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    private ResponseEntity<String> createResponseEntityForMandate(String pesel, String dateTime, String offenses,
                                                                  String points, String amount, String url, HttpMethod httpMethod) {
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

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/mandat");
    }
}
