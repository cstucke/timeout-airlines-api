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
    public ResponseEntity<Book> bookFlight(@RequestParam Long flightId, @RequestParam Long passportNumber, @RequestParam String seatType) {
        return ResponseEntity.ok(bookService.bookFlight(flightId, passportNumber, seatType));
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
    public ResponseEntity<Book> updateBooking(@PathVariable Long id, @RequestParam String seatType) {
        return ResponseEntity.ok(bookService.updateSeatType(id, seatType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
