package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
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
@RequiredArgsConstructor
@RequestMapping(path = "/versioning/headers/users")
public class UserCustomHeaderVersioningController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ModelMapper modelMapper;

    @GetMapping(path = "/{id}", headers = "x-api-version=1")
    public UserDtoV1 getUserByIdV1(@PathVariable(name = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        return modelMapper.map(user, UserDtoV1.class);
    }

    @GetMapping(path = "/{id}", headers = "x-api-version=2")
    public UserDtoV2 getUserByIdV2(@PathVariable(name = "id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        return modelMapper.map(user, UserDtoV2.class);
    }
}
