package com.pirko.mandaty.service;

import com.pirko.mandaty.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendEmail(Person person, String subject, String text) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setSubject(subject);
        smm.setTo(person.getEmail());
        smm.setText(text);
        mailSender.send(smm);
    }

}
