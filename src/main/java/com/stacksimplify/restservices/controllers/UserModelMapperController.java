package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(path = "/model-mapper/users")
@RequiredArgsConstructor
public class UserModelMapperController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping(path = "/{id}")
    public UserMmDto getUserById(@PathVariable(name = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
        return userMmDto;
    }
}
