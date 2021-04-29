package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.api.UserModelAssembler;
import com.ailtonluiz.sgdapi.api.model.UserModel;
import com.ailtonluiz.sgdapi.domain.model.User;
import com.ailtonluiz.sgdapi.domain.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UsersController {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public List<UserModel> listar() {
        List<User> allUsers = usersRepository.findAll();

        return userModelAssembler.toCollectionModel(allUsers);
    }
}
