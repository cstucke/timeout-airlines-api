package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="bookings")
public class Book {

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private String typeOfSeat; // It can be one of those : "FIRST", "PREMIUM", "BUSINESS", "ECONOMY"
}
