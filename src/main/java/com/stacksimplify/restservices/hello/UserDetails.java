package com.stacksimplify.restservices.hello;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetails {
    private String firstname;
    private String lastname;
    private String city;
}
