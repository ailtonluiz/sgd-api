package com.ailtonluiz.sgdapi.api;

import com.ailtonluiz.sgdapi.api.model.UserModel;
import com.ailtonluiz.sgdapi.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserModel toModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public List<UserModel> toCollectionModel(Collection<User> users) {
        return users.stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());
    }
}
