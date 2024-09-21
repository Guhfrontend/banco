package com.nutrilife.banco.service;

import com.nutrilife.banco.DTO.ClientDTO;
import com.nutrilife.banco.domain.Client;
import com.nutrilife.banco.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return clientRepository.findById(id).get();
    }
}
