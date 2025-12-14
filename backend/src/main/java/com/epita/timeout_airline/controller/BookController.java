package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookController {

    @Autowired
    private BookService bookService;

    // Inline DTO for student-style simplicity
    public static class BookingRequest {
        public Long flightId;
        public Long clientId;
        public String seatType;
    }

    @PostMapping
    public Map<String, Object> bookFlight(@RequestBody BookingRequest request) {
        return bookService.bookFlight(request.flightId, request.clientId, request.seatType);
    }
}
