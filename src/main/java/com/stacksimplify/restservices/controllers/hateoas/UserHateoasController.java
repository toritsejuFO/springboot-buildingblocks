package com.stacksimplify.restservices.controllers.hateoas;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(path = "/hateoas/users")
@RequiredArgsConstructor
public class UserHateoasController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException{
        List<User> users = userService.getAllUsers();
        for (User user: users) {
            // Self link for each users
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
            user.add(selfLink);

            // Relationship link with get
            CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getUserId());
            Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("orders");
            user.add(ordersLink);
        }

        Link usersLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return CollectionModel.of(users, usersLink);
    }

    @GetMapping(path = "/{id}")
    public EntityModel<User> getUserById(@PathVariable(name = "id") @Min(1) Long id) {
        try {
            Optional<User> optionalUser = userService.getUserById(id);
            User user = optionalUser.get();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getUserId()).withSelfRel();
            user.add(selfLink);
            return EntityModel.of(user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
