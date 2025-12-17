package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.*;
import com.epita.timeout_airline.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final ClientRepository clientRepository;
    private final MilesRewardRepository milesRewardRepository;

    public Booking bookFlight(Long flightId, Long clientId, String seatType) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new RuntimeException("Flight not found"));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));

        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setClient(client);
        booking.setTypeOfSeat(seatType);

        Booking savedBooking = bookingRepository.save(booking);

        MilesReward reward = new MilesReward();
        reward.setClient(client);
        reward.setFlight(flight);
        reward.setDate(LocalDate.now());

        milesRewardRepository.save(reward);

        LocalDate startYear = LocalDate.now().withDayOfYear(1);
        LocalDate endYear = LocalDate.now().withMonth(12).withDayOfMonth(31);

        long flightsThisYear = milesRewardRepository.countByClientAndDateBetween(client, startYear, endYear);

        if (flightsThisYear == 3) {
            String discountCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            reward.setDiscountCode(discountCode);
            milesRewardRepository.save(reward);
        }

        return savedBooking;

    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking updateSeatType(Long id, String seatType) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setTypeOfSeat(seatType);
        return bookingRepository.save(booking);
    }

    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

}
