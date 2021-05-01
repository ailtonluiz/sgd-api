package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Client;
import com.ailtonluiz.sgdapi.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {

        return clientRepository.save(client);
    }

    public void delete(Long clientId) {
        try {
            clientRepository.deleteById(clientId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no client registration with code %d", clientId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code client %d cannot be removed as it is in use", clientId));
        }
    }
}
