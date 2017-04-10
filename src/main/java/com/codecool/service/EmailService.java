package com.codecool.service;

import com.codecool.model.Email;
import com.codecool.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private SimpleMailMessage message;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public Email findById(String id) {
        return emailRepository.findById(id);
    }

    public void sendEmail(Email email) {
        message = new SimpleMailMessage();
        message.setTo(email.getReceiver());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        javaMailSender.send(message);
    }
}
