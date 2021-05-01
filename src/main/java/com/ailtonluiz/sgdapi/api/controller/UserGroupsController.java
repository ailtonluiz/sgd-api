package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.UserGroup;
import com.ailtonluiz.sgdapi.domain.repository.UserGroupRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterUserGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/userGroups")
public class UserGroupsController {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private RegisterUserGroupService registerUserGroupService;

    @GetMapping
    public List<UserGroup> list() {
        return userGroupRepository.findAll();
    }

    @GetMapping("/{userGroupId}")
    public ResponseEntity<UserGroup> search(@PathVariable Long userGroupId) {
        Optional<UserGroup> userGroup = userGroupRepository.findById(userGroupId);

        if (userGroup.isPresent()) {
            return ResponseEntity.ok(userGroup.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroup add(@RequestBody UserGroup userGroup) {
        return registerUserGroupService.save(userGroup);
    }

    @PutMapping("/{userGroupId}")
    public ResponseEntity<UserGroup> update(@PathVariable Long userGroupId,
                                          @RequestBody UserGroup userGroup) {
        UserGroup currentUserGroup = userGroupRepository.findById(userGroupId).orElse(null);

        if (currentUserGroup != null) {
            BeanUtils.copyProperties(userGroup, currentUserGroup, "id");

            currentUserGroup = registerUserGroupService.save(currentUserGroup);
            return ResponseEntity.ok(currentUserGroup);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userGroupId}")
    public ResponseEntity<?> remove(@PathVariable Long userGroupId) {
        try {
            registerUserGroupService.delete(userGroupId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
