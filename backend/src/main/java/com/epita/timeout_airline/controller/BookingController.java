package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.model.Booking;
import com.epita.timeout_airline.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> bookFlight(@RequestBody java.util.Map<String, Object> payload) {
        Long flightId = Long.valueOf(payload.get("flightId").toString());
        Long clientId = Long.valueOf(payload.get("clientId").toString());
        String seatType = (String) payload.get("seatType");

        return ResponseEntity.ok(bookingService.bookFlight(flightId, clientId, seatType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        return bookingService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    // Change seat type
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody java.util.Map<String, Object> payload) {
        String seatType = (String) payload.get("seatType");

        if (seatType == null) {
            seatType = (String) payload.get("typeOfSeat");
        }

        return ResponseEntity.ok(bookingService.updateSeatType(id, seatType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
