package com.codecool.service;

import com.codecool.model.Client;
import com.codecool.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client findByApiKey(String apiKey) {
        return clientRepository.findByApiKey(apiKey);
    }
}
