package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.model.Airport;
import com.epita.timeout_airline.model.Flight;
import com.epita.timeout_airline.model.Plane;
import com.epita.timeout_airline.repository.AirportRepository;
import com.epita.timeout_airline.repository.PlaneRepository;
import com.epita.timeout_airline.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    private final PlaneRepository planeRepository;

    private final AirportRepository airportRepository;

    @PostMapping
    public ResponseEntity<Flight> createFlight(
            @RequestParam Long planeId,
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestBody Flight flight
    ) {
        Plane plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new RuntimeException("Plane not found"));

        Airport dep = airportRepository.findById(departureAirportId)
                .orElseThrow(() -> new RuntimeException("Departure airport not found"));

        Airport arr = airportRepository.findById(arrivalAirportId)
                .orElseThrow(() -> new RuntimeException("Arrival airport not found"));

        flight.setPlane(plane);
        flight.setDepartureAirport(dep);
        flight.setArrivalAirport(arr);

        return ResponseEntity.ok(flightService.saveFlight(flight));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable Long id) {
        return flightService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String departureCity, @RequestParam String arrivalCity, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date ) {
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, date);
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(
            @PathVariable Long id,
            @RequestParam Long planeId,
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestBody Flight flightDetails
    ) {
        Plane plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new RuntimeException("Plane not found"));

        Airport dep = airportRepository.findById(departureAirportId)
                .orElseThrow(() -> new RuntimeException("Departure airport not found"));

        Airport arr = airportRepository.findById(arrivalAirportId)
                .orElseThrow(() -> new RuntimeException("Arrival airport not found"));

        flightDetails.setPlane(plane);
        flightDetails.setDepartureAirport(dep);
        flightDetails.setArrivalAirport(arr);

        return ResponseEntity.ok(flightService.updateFlight(id, flightDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
