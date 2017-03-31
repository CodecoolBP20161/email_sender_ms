package com.codecool.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Client {

    @Id
    @Setter(AccessLevel.NONE)
    private String id = UUID.randomUUID().toString();

    private String userName;

    @Setter(AccessLevel.NONE)
    private String ApiKey = UUID.randomUUID().toString();

    private String serverAddress;

    private String emailAddress;

    private String emailPassword;

    private int portNumber;

}
