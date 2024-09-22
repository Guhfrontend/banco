package com.nutrilife.banco.resource;


import com.nutrilife.banco.DTO.ClientDTO;
import com.nutrilife.banco.domain.Client;
import com.nutrilife.banco.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestScope
@RestController
@RequestMapping("/clients")
public class ClientResource {

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
    public ResponseEntity<ClientDTO> findById(@PathVariable UUID id) {
        ClientDTO clientDTO = new ClientDTO(clientService.findById(id));

        if (clientDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(clientService.fromDTO(clientDTO)));
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = clientService.save(clientService.fromDTO(clientDTO));

        if (client == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok().body(new ClientDTO(client));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        Client client = clientService.findById(id);
        if (client == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        clientService.deleteById(id);
    }
}
