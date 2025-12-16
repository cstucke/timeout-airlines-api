package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private String departureCity;
    private String arrivalCity;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    @ManyToOne
    private Plane plane;

    private int numberOfSeat;

    private double firstClassSeatPrice;
    private double premiumSeatPrice;
    private double businessClassPrice;
    private double economicsClassPrice;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private java.util.List<Book> bookings;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private java.util.List<MilesReward> rewards;

}
