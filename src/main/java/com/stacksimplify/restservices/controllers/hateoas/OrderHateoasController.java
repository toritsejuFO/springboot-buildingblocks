package com.stacksimplify.restservices.controllers.hateoas;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.respositories.OrderRepository;
import com.stacksimplify.restservices.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/hateoas/users")
@RequiredArgsConstructor
public class OrderHateoasController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @GetMapping(path = "/{user_id}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable(name = "user_id") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        List<Order> orders = user.get().getOrders();
        return CollectionModel.of(orders);
    }

}
