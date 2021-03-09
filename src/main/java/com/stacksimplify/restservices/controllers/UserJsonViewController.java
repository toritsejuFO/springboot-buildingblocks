package com.stacksimplify.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.View;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping(path = "/json-view/users")
@Validated
@RequiredArgsConstructor
public class UserJsonViewController {

    @Autowired
    private final UserService userService;

    @JsonView(View.External.class)
    @GetMapping(path = "/external/{id}")
    public Optional<User> getUserByIdExternal(@PathVariable(name = "id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @JsonView(View.Internal.class)
    @GetMapping(path = "/internal/{id}")
    public Optional<User> getUserByIdInternal(@PathVariable(name = "id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
