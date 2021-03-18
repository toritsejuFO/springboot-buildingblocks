package com.stacksimplify.restservices.entities;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "This is the User model")
//@JsonIgnoreProperties({"firstname", "lastname"})
//@JsonFilter("userFilter")
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(View.External.class)
    @ApiModelProperty(notes = "Auto generated unique Id", required = true, position = 1)
    private Long userId;

    @NotEmpty(message = "Username is mandatory field. Please provide username.")
    @Column(name = "username", length = 50, nullable = false, unique = true)
    @JsonView(View.External.class)
    @ApiModelProperty(notes = "Username should be unique", example = "kreddy", required = false, position = 2)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @JsonView(View.External.class)
    private String email;

    @Size(min = 2, max =  50, message = "FirstName should have at least 2 characters, and 50 max.")
    @Column(name = "firstname", length = 50, nullable = false, unique = false)
    @JsonView(View.External.class)
    private String firstname;

    @Size(min = 2, max =  50, message = "LastName should have at least 2 characters, and 50 max.")
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

    @Column(name = "address", length = 50)
    private String address;
}
