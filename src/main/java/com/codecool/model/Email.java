package com.codecool.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class Email {

    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private Client client;

    private String subject;

    @ElementCollection
    @CollectionTable(name = "receivers", joinColumns = @JoinColumn(name = "email_id"))
    private Set<String> receivers = new HashSet<>();

    private String body;

    @org.hibernate.validator.constraints.Email
    private String cc;

    @org.hibernate.validator.constraints.Email
    private String bcc;

    public void setReceivers(@org.hibernate.validator.constraints.Email String receiver) {
        this.receivers.add(receiver);
    }
}
