package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Unity;
import com.ailtonluiz.sgdapi.domain.repository.UnityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterUnityService {

    @Autowired
    private UnityRepository unityRepository;

    public Unity save(Unity unity) {

        return unityRepository.save(unity);
    }

    public void delete(Long unityId) {
        try {
            unityRepository.deleteById(unityId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no unity registration with code %d", unityId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code unity %d cannot be removed as it is in use", unityId));
        }
    }
}
