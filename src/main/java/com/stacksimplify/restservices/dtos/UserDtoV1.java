package com.stacksimplify.restservices.dtos;

import com.stacksimplify.restservices.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoV1 {
    private Long userId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    private String ssn;
    private List<Order> orders;
}
