package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.Airport;
import com.epita.timeout_airline.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;

    @Transactional
    public Airport registerAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Transactional(readOnly = true)
    public Optional<Airport> findById(Long id) {
        return airportRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }

    @Transactional
    public Airport updateAirport(Long id, Airport airportDetails) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport DNE, id: " + id));

        airport.setName(airportDetails.getName());
        airport.setCity(airportDetails.getCity());
        airport.setCountry(airportDetails.getCountry());

        return airportRepository.save(airport);
    }

    @Transactional
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }

}
