package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.*;
import com.epita.timeout_airline.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final FlightRepository flightRepository;
    private final ClientRepository clientRepository;
    private final MilesRewardRepository milesRewardRepository;

    public Book bookFlight(
            Long flightId,
            Long passportNumber,
            String seatType
    ) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));

        Client client = clientRepository.findById(passportNumber).orElseThrow(() -> new RuntimeException("Client not found"));

        Book booking = new Book();
        booking.setFlight(flight);
        booking.setClient(client);
        booking.setTypeOfSeat(seatType);

        Book savedBooking = bookRepository.save(booking);

        MilesReward reward = new MilesReward();
        reward.setClient(client);
        reward.setFlight(flight);
        reward.setDate(LocalDate.now());

        milesRewardRepository.save(reward);

        LocalDate startYear = LocalDate.now().withDayOfYear(1);
        LocalDate endYear = LocalDate.now().withMonth(12).withDayOfMonth(31);

        long flightsThisYear = milesRewardRepository.countByClientAndDateBetween(client, startYear, endYear);

        if (flightsThisYear == 3) {String discountCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            System.out.println("Discount code generated for client : " + discountCode);
        }

        return savedBooking;

    }
}
