package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.respositories.OrderRepository;
import com.stacksimplify.restservices.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @PostMapping(path = "/{user_id}/orders")
    public Order createOrder(@PathVariable(name = "user_id") Long userId, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> exitingUser = userRepository.findById(userId);
        if (exitingUser.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        User user = exitingUser.get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    @GetMapping(path = "/{user_id}/orders")
    public List<Order> getAllOrders(@PathVariable(name = "user_id") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        return user.get().getOrders();
    }

    @GetMapping(path = "/orders/{order_id}")
    public Order getOrderByOrderId(@PathVariable(name = "order_id") Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        }
        return order.get();
    }
}
