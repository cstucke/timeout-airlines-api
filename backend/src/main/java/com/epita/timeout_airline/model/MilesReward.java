package com.epita.timeout_airline.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name="miles_rewards")
public class MilesReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name="flight_id", nullable=false)
    @JsonIgnore
    private Flight flight;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private String discountCode;
}
