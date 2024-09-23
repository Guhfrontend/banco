package com.nutrilife.banco.service;

import com.nutrilife.banco.DTO.ClientDTO;
import com.nutrilife.banco.domain.Client;
import com.nutrilife.banco.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client fromDTO(ClientDTO ClientDTO){
        return new Client(ClientDTO.getName(), ClientDTO.getEmail(), ClientDTO.getPassword(), ClientDTO.getBalance());
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void deleteById(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        clientRepository.deleteById(id);

    }
}
