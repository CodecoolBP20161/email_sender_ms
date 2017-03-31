package com.codecool.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
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
    @org.hibernate.validator.constraints.Email
    @Valid
    private Set<String> receivers = new HashSet<>();

    private String body;

    @org.hibernate.validator.constraints.Email
    private String cc;

    @org.hibernate.validator.constraints.Email
    private String bcc;

    @org.hibernate.validator.constraints.Email
    public void setReceivers(String receiver) {
        this.receivers.add(receiver);
    }

    public void setReceivers(Set<String> receivers) {
        this.receivers = receivers;
    }

}
