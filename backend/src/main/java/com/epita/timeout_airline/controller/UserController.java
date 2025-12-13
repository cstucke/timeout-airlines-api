package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epita.timeout_airline.model.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/addPerson")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = userRepository.save(user);
        return ResponseEntity.ok(response);
    }
}
