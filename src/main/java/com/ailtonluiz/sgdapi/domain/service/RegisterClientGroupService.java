package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.ClientGroup;

import com.ailtonluiz.sgdapi.domain.repository.ClientGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterClientGroupService {

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    public ClientGroup save(ClientGroup clientGroup) {

        return clientGroupRepository.save(clientGroup);
    }

    public void delete(Long clientGroupId) {
        try {
            clientGroupRepository.deleteById(clientGroupId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no clientGroup registration with code %d", clientGroupId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code clientGroup %d cannot be removed as it is in use", clientGroupId));
        }
    }
}
