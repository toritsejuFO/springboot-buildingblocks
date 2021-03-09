package com.stacksimplify.restservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/jackson-filter/users")
@Validated
@RequiredArgsConstructor
public class UserMappingJacksonController {

    @Autowired
    private final UserService userService;

//    Fields with hashset - static params
    @GetMapping(path = "/{id}")
    public MappingJacksonValue getUserById(@PathVariable(name = "id") @Min(1) Long id) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();

            Set<String> fields = new HashSet<>();
            fields.add("userId");
            fields.add("username");
            fields.add("ssn");
            fields.add("orders");
            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return mapper;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

//    Fields with params
    @GetMapping(path = "/params/{id}")
    public MappingJacksonValue getUserByIdParams(@PathVariable(name = "id") @Min(1) Long id, @RequestParam Set<String> fields) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();

            FilterProvider filterProvider = new SimpleFilterProvider()
                    .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));

            MappingJacksonValue mapper = new MappingJacksonValue(user);
            mapper.setFilters(filterProvider);
            return mapper;
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
