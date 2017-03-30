package com.codecool.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class Client {

    private String id;

    private String userName;

    private String ApiKey = UUID.randomUUID().toString();

    private String serverAddress;

    private String emailAddress;

    private String emailPassword;

    private int portNumber;
}
