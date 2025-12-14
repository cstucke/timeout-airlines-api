package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="airports")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

}
