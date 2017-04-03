package com.codecool.model;

import com.codecool.exception.InvalidEmailException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ConstraintTarget;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    public void setReceivers(String receiver) throws InvalidEmailException {
        if (!receiver.matches("^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")) {
            throw new InvalidEmailException();
        } else {
            this.receivers.add(receiver);
        }
    }
}
