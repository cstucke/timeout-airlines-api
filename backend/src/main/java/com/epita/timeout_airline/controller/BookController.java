package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.model.Book;
import com.epita.timeout_airline.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> bookFlight(@RequestBody java.util.Map<String, Object> payload) {
        Long flightId = Long.valueOf(payload.get("flightId").toString());
        Long clientId = Long.valueOf(payload.get("clientId").toString());
        String seatType = (String) payload.get("seatType");

        return ResponseEntity.ok(bookService.bookFlight(flightId, clientId, seatType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBooking(@PathVariable Long id) {
        return bookService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBookings() {
        return ResponseEntity.ok(bookService.findAll());
    }

    // Change seat type
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBooking(@PathVariable Long id, @RequestBody java.util.Map<String, Object> payload) {
        String seatType = (String) payload.get("seatType");

        if (seatType == null) {
            seatType = (String) payload.get("typeOfSeat");
        }

        return ResponseEntity.ok(bookService.updateSeatType(id, seatType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
