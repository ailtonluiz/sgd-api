package com.ailtonluiz.sgdapi.api.v1.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.ClientGroup;
import com.ailtonluiz.sgdapi.domain.repository.ClientGroupRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterClientGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clientGroups")
public class ClientGroupsController {

    @Autowired
    private ClientGroupRepository clientGroupRepository;

    @Autowired
    private RegisterClientGroupService registerClientGroupService;

    @GetMapping
    public List<ClientGroup> list() {
        return clientGroupRepository.findAll();
    }

    @GetMapping("/{clientGroupId}")
    public ResponseEntity<ClientGroup> search(@PathVariable Long clientGroupId) {
        Optional<ClientGroup> clientGroup = clientGroupRepository.findById(clientGroupId);
        if (clientGroup.isPresent()) {
            return ResponseEntity.ok(clientGroup.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientGroup add(@RequestBody ClientGroup clientGroup) {
        return registerClientGroupService.save(clientGroup);
    }

    @PutMapping("/{clientGroupId}")
    public ResponseEntity<ClientGroup> update(@PathVariable Long clientGroupId,
                                         @RequestBody ClientGroup clientGroup) {
        ClientGroup currentClientGroup = clientGroupRepository.findById(clientGroupId).orElse(null);

        if (currentClientGroup != null) {
            BeanUtils.copyProperties(clientGroup, currentClientGroup, "id");

            currentClientGroup = registerClientGroupService.save(currentClientGroup);
            return ResponseEntity.ok(currentClientGroup);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clientGroupId}")
    public ResponseEntity<?> remove(@PathVariable Long clientGroupId) {
        try {
            registerClientGroupService.delete(clientGroupId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }


}
