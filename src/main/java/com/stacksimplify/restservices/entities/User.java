package com.stacksimplify.restservices.entities;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@JsonIgnoreProperties({"firstname", "lastname"})
//@JsonFilter("userFilter")
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.External.class)
    private Long userId;

    @NotEmpty(message = "Username is mandatory field. Please provide username.")
    @Column(name = "username", length = 50, nullable = false, unique = true)
    @JsonView(View.External.class)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @JsonView(View.External.class)
    private String email;

    @Size(min = 2, message = "FirstName should have at least 2 characters.")
    @Column(name = "firstname", length = 50, nullable = false, unique = false)
    @JsonView(View.External.class)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false, unique = false)
    @JsonView(View.External.class)
    private String lastname;

    @Column(name = "role", length = 50, nullable = false, unique = false)
    @JsonView(View.Internal.class)
    private String role;

    //    @JsonIgnore
    @Column(name = "ssn", length = 50, nullable = false, unique = true)
    @JsonView(View.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(View.Internal.class)
    private List<Order> orders;
}
