package com.example.home18.Model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

import java.util.Iterator;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements org.apache.catalina.@Valid User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "Username cannot be null")
    @Size(min = 5, message = "Username must be at least 5 characters long")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Role cannot be null")
    private String role;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be a positive integer")
    private Integer age;

}
