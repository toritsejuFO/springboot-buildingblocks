package com.stacksimplify.restservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "firstname", length = 50, nullable = false, unique = false)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false, unique = false)
    private String lastname;

    @Column(name = "role", length = 50, nullable = false, unique = false)
    private String role;

    @Column(name = "ssn", length = 50, nullable = false, unique = true)
    private String ssn;
}
