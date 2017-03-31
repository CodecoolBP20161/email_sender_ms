package com.codecool.service;

import com.codecool.model.Client;
import com.codecool.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    @Autowired
    ClientRepository repository;

    @PostConstruct
    public void init() {

        if (repository.findAll().size() == 0) {
            Client c1 = new Client();
            c1.setUserName("robotkutya");
            c1.setEmailAddress("hccrm.robotkutya@gmail.com");
            c1.setEmailPassword("codecool");
            c1.setPortNumber(587);
            c1.setServerAddress("smtp.gmail.com");
            repository.save(c1);
        }

    }
}
