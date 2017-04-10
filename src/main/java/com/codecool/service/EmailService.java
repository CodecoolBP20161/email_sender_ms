package com.codecool.service;

import com.codecool.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private SimpleMailMessage message;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(Email email) {
        message = new SimpleMailMessage();
        message.setTo(email.getReceiver());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
    }
}
