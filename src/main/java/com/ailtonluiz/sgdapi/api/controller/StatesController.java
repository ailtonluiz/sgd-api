package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.State;
import com.ailtonluiz.sgdapi.domain.repository.StateRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterStateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/states")
public class StatesController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RegisterStateService registerStateService;

    @GetMapping
    public List<State> list() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> search(@PathVariable Long stateId) {
        Optional<State> state = stateRepository.findById(stateId);

        if (state.isPresent()) {
            return ResponseEntity.ok(state.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return registerStateService.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId,
                                        @RequestBody State state) {
        State currentState = stateRepository.findById(stateId).orElse(null);

        if (currentState != null) {
            BeanUtils.copyProperties(state, currentState, "id");

            currentState = registerStateService.save(currentState);
            return ResponseEntity.ok(currentState);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            registerStateService.delete(stateId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }


}
