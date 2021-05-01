package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.UserGroup;
import com.ailtonluiz.sgdapi.domain.repository.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterUserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    public UserGroup save(UserGroup userGroup) {

        return userGroupRepository.save(userGroup);
    }

    public void delete(Long userGroupId) {
        try {
            userGroupRepository.deleteById(userGroupId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no userGroup registration with code %d", userGroupId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code userGroup %d cannot be removed as it is in use", userGroupId));
        }
    }
}
