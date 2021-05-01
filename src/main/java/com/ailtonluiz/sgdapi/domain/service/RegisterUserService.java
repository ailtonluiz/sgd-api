package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.User;
import com.ailtonluiz.sgdapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterUserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {

        return userRepository.save(user);
    }

    public void delete(Long userId) {
        try {
            userRepository.deleteById(userId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no user registration with code %d", userId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code user %d cannot be removed as it is in use", userId));
        }
    }
}
