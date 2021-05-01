package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.State;
import com.ailtonluiz.sgdapi.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterStateService {

    @Autowired
    private StateRepository stateRepository;

    public State save(State state) {

        return stateRepository.save(state);
    }

    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no state registration with code %d", stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code state %d cannot be removed as it is in use", stateId));
        }
    }
}
