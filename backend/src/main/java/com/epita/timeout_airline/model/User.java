package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private String address;
    @Column(nullable = false, unique=true)
    private String email;
    private String phone;
    @Column(nullable = false)
    private LocalDate birthdate;

}
