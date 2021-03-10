package com.stacksimplify.restservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMsDto {
    private Long userId;
    private String username;
    private String emailaddress;
    private String rolename;
}
