package com.nutrilife.banco.resource;


import com.nutrilife.banco.DTO.ClientDTO;
import com.nutrilife.banco.domain.Client;
import com.nutrilife.banco.repository.ClientRepository;
import com.nutrilife.banco.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScope
@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> findAll() {
        List <ClientDTO> listDto = clientService.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

        if (listDto.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(UUID id) {
        Client client = clientService.findById(id);
        ClientDTO clientDTO = new ClientDTO(client);

        if (clientDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(clientDTO);
    }


}
