package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailService mailService;

    @Test
    void shouldSendEmail() {
        //given
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject("test");
        smm.setTo("email");
        smm.setText("text");
        Person person = new Person();
        person.setEmail("email");
        //when
        mailService.sendEmail(person, "test", "text");
        //then
        verify(mailSender, times(1)).send(smm);
    }

}
