package com.stacksimplify.restservices.dtos;

import com.stacksimplify.restservices.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserMmDto {
    private Long userId;
    private String username;
    private String firstname;
    private String lastname;
    private List<Order> orders;
}
