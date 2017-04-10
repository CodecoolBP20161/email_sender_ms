package com.codecool.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Data
@Entity
public class Email {

    @Id
    @Setter(AccessLevel.NONE)
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    private Client client;

    private String subject;

    @org.hibernate.validator.constraints.Email
    private String receiver;

    private String body;

    @org.hibernate.validator.constraints.Email
    private String cc;

    @org.hibernate.validator.constraints.Email
    private String bcc;
}
