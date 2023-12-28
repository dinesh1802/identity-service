package com.ekart.identityservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "User Name cannot be empty")
    private String userName;

    @Email(message = "Enter a valid email address")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Enter a valid contact number")
    private String contactNumber;

    private String password;

}

