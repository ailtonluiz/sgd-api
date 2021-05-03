package com.ailtonluiz.sgdapi.api.v1.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Client;
import com.ailtonluiz.sgdapi.domain.repository.ClientRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clients")
public class ClientsController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RegisterClientService registerClientService;

    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> search(@PathVariable Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@RequestBody Client client) {
        return registerClientService.save(client);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> update(@PathVariable Long clientId,
                                          @RequestBody Client client) {
        Client currentClient = clientRepository.findById(clientId).orElse(null);

        if (currentClient != null) {
            BeanUtils.copyProperties(client, currentClient, "id");

            currentClient = registerClientService.save(currentClient);
            return ResponseEntity.ok(currentClient);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> remove(@PathVariable Long clientId) {
        try {
            registerClientService.delete(clientId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
