package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/map-struct/users")
@RequiredArgsConstructor
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.userMsDtosFrom(users);
    }

    @GetMapping(path = "/{id}")
    public UserMsDto getUserById(@PathVariable(name = "id") @Min(1) Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return userMapper.userMsDtoFrom(user);
    }
}
