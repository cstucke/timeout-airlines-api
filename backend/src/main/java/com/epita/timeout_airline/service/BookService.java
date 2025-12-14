package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.*;
import com.epita.timeout_airline.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MilesRewardRepository milesRewardRepository;

    public Map<String, Object> bookFlight(Long flightId, Long clientId, String seatType) {

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        long bookedSeats = bookRepository.countByFlightId(flight.getId());
        if (bookedSeats >= flight.getNumberOfSeats()) {
            throw new RuntimeException("No available seats");
        }

        Book booking = new Book();
        booking.setFlight(flight);
        booking.setClient(client);
        booking.setTypeOfSeat(seatType);
        bookRepository.save(booking);

        MilesReward reward = new MilesReward();
        reward.setClient(client);
        reward.setFlight(flight);
        reward.setDate(LocalDate.now());
        milesRewardRepository.save(reward);

        int year = LocalDate.now().getYear();
        long flightsThisYear = milesRewardRepository.countByClientAndDateBetween(
                client, LocalDate.of(year,1,1), LocalDate.of(year,12,31)
        );

        String discountCode = null;
        if (flightsThisYear >= 3) {
            discountCode = UUID.randomUUID().toString().substring(0,8).toUpperCase();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("booking", booking);
        response.put("discountCode", discountCode);

        return response;
    }
}
