package com.epita.timeout_airline.service;

import com.epita.timeout_airline.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.epita.timeout_airline.model.Client;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client registerClient(Client client) {
        return clientRepository.save(client);
    }

}
