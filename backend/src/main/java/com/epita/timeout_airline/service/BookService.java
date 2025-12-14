package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.Book;
import com.epita.timeout_airline.model.Client;
import com.epita.timeout_airline.model.Flight;
import com.epita.timeout_airline.repository.BookRepository;
import com.epita.timeout_airline.repository.ClientRepository;
import com.epita.timeout_airline.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

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

        Map<String, Object> response = new HashMap<>();
        response.put("bookingId", booking.getId());
        response.put("flightNumber", flight.getFlightNumber());
        response.put("clientId", client.getId());
        response.put("seatType", seatType);

        return response;
    }
}
