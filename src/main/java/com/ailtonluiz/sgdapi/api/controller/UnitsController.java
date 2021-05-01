package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Unity;
import com.ailtonluiz.sgdapi.domain.repository.UnityRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterUnityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/units")
public class UnitsController {

    @Autowired
    private UnityRepository unityRepository;

    @Autowired
    private RegisterUnityService registerUnityService;

    @GetMapping
    public List<Unity> list() {

        return unityRepository.findAll();
    }

    @GetMapping("/{unityId}")
    public ResponseEntity<Unity> search(@PathVariable Long unityId) {
        Optional<Unity> unity = unityRepository.findById(unityId);

        if (unity.isPresent()) {
            return ResponseEntity.ok(unity.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Unity add(@RequestBody Unity unity) {
        return registerUnityService.save(unity);
    }

    @PutMapping("/{unityId}")
    public ResponseEntity<Unity> update(@PathVariable Long unityId,
                                        @RequestBody Unity unity) {
        Optional<Unity> currentUnity = unityRepository.findById(unityId);

        if (currentUnity.isPresent()) {
            BeanUtils.copyProperties(unity, currentUnity.get(), "id");

            Unity unitySalva = registerUnityService.save(currentUnity.get());
            return ResponseEntity.ok(unitySalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unityId}")
    public ResponseEntity<?> remove(@PathVariable Long unityId) {
        try {
            registerUnityService.delete(unityId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
