package com.epita.timeout_airline.service;

import com.epita.timeout_airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.epita.timeout_airline.model.Flight;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public List<Flight> searchFlights(String departureCity, String arrivalCity, LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);
        return flightRepository.findByDepartureCityAndArrivalCityAndDepartureTimeBetween(
                departureCity, arrivalCity, start, end
        );
    }

    @Transactional
    public Flight updateFlight(Long id, Flight details) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found, id: " + id));

        flight.setFlightNumber(details.getFlightNumber());
        flight.setDepartureCity(details.getDepartureCity());
        flight.setArrivalCity(details.getArrivalCity());
        flight.setDepartureTime(details.getDepartureTime());
        flight.setArrivalTime(details.getArrivalTime());
        flight.setNumberOfSeat(details.getNumberOfSeat());
        flight.setFirstClassSeatPrice(details.getFirstClassSeatPrice());
        flight.setPremiumSeatPrice(details.getPremiumSeatPrice());
        flight.setBusinessClassPrice(details.getBusinessClassPrice());
        flight.setEconomicsClassPrice(details.getEconomicsClassPrice());

        flight.setPlane(details.getPlane());
        flight.setDepartureAirport(details.getDepartureAirport());
        flight.setArrivalAirport(details.getArrivalAirport());

        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}