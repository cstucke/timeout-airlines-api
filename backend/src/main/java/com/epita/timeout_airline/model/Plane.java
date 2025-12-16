package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="planes")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int manufactureYear;

}