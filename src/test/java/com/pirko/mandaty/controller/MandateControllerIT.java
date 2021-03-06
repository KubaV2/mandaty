package com.pirko.mandaty.controller;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.pirko.mandaty.common.ResponseEntityCreator;
import com.pirko.mandaty.model.Person;
import com.pirko.mandaty.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import javax.mail.internet.MimeMessage;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MandateControllerIT {

    @LocalServerPort
    private int serverPort;

    @Autowired
    private PersonService personService;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test", "springboot"))
            .withPerMethodLifecycle(false);

    private URI createServerAddress() throws URISyntaxException {
        return new URI("http://localhost:" + serverPort + "/mandat");
    }

    @Test
    void shouldReturn3xxStatusWhenAddMandateSuccessfully() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForMandate(
                "94051913613", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);
        //then
        assertTrue(response.getStatusCode().is3xxRedirection());
    }

    @Test
    void shouldReturn2xxStatusWhenAddMandateIsNotSuccesfullyBecauseReturnNewHtmlViewWithException() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForMandate(
                "", "",
                "", "", "", createServerAddress() + "/wystaw", HttpMethod.POST);
        //then
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenPeselIsNotInTheDatabase() throws Exception {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForMandate(
                "97122838587", "2022-05-10T23:57:58",
                "", "10", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        String message = "{\"message\":\"Brak osoby z numerem PESEL: 97122838587 w bazie danych.\"}";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }


    @Test
    void shouldReturn4xxStatusAndExceptionWhenSameMandateExistInDB() throws URISyntaxException {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForMandate("1", "94051913613",
                "2022-05-11T17:36:38", "", "10", "100", createServerAddress() +
                        "/wystaw", HttpMethod.POST);

        String message = "{\"message\":\"Ten mandat jest ju?? w bazie danych\"}";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

    @Test
    void shouldSendEmailWhenPersonHasMaxPoints() throws Exception {
        //when
        Person person = personService.findPersonByPesel("94051913613");
        ResponseEntity<String> response = ResponseEntityCreator.createForMandate("94051913613", "2022-05-10T23:37:58",
                "", "12", "100", createServerAddress() + "/wystaw", HttpMethod.POST);

        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage receivedMessage = emails[0];
        //then
        assertEquals(2, emails.length);
        assertEquals(person.getEmail(), receivedMessage.getAllRecipients()[0].toString());
        assertEquals("UWAGA! Przekroczony dopuszczalny limit punktow karnych", receivedMessage.getSubject());
        assertEquals("W zwiazku z przekroczeniem dozwolonej ilosci punktow karnych(" +
                "24" + ") odebrano ci prawo jazdy.", GreenMailUtil.getBody(receivedMessage));

    }

    @Test
    void shouldReturn3xxStatusWhenDeleteMandateSuccessfully() throws URISyntaxException {
        //when
        ResponseEntity<String> response = ResponseEntityCreator.createForDelete(createServerAddress() +
                "/usun/1", HttpMethod.DELETE);
        //then
        assertTrue(response.getStatusCode().is3xxRedirection());
    }

    @Test
    void shouldReturn4xxStatusAndExceptionWhenTryDeleteNotExistMandate() throws URISyntaxException {
        //when
        Long id = 0L;
        ResponseEntity<String> response = ResponseEntityCreator.createForDelete(createServerAddress() +
                "/usun/" + id, HttpMethod.DELETE);

        String message = "{\"message\":\"Nie mo??na wykona?? operacji usuwania. Brak mandatu o ID " + id + "\"}";
        //then
        assertEquals(message, response.getBody());
        assertTrue(response.getStatusCode().is4xxClientError());
    }

}
