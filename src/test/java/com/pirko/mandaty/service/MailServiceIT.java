package com.pirko.mandaty.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MailServiceIT {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test", "springboot"))
            .withPerMethodLifecycle(false);

    @Autowired
    private MailService mailService;

    @Test
    void shouldSendMail() throws MessagingException {
        //when
        mailService.sendEmail("test@adress.com", "test_subject", "test_text");
        MimeMessage[] emails = greenMail.getReceivedMessages();
        MimeMessage receivedMessage = emails[0];
        //then
        assertEquals(1, emails.length);
        assertEquals("test@adress.com", receivedMessage.getAllRecipients()[0].toString());
        assertEquals("test_subject", receivedMessage.getSubject());
        assertEquals("test_text", GreenMailUtil.getBody(receivedMessage));

    }
}