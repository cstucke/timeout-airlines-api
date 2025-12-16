package com.epita.timeout_airline.service;

import com.epita.timeout_airline.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.epita.timeout_airline.model.Flight;
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

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}