package com.epita.timeout_airline.controller;

import com.epita.timeout_airline.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.epita.timeout_airline.model.Plane;
import java.util.List;

@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping
    public ResponseEntity<Plane> addPlane(@RequestBody Plane plane) {
        Plane savedPlane = planeService.registerPlane(plane);
        return ResponseEntity.ok().body(savedPlane);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlane(@PathVariable Long id) {
        return planeService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Plane>> getAllPlanes() {
        return ResponseEntity.ok(planeService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable Long id, @RequestBody Plane plane) {
        return ResponseEntity.ok(planeService.updatePlane(id, plane));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        planeService.deletePlane(id);
        return ResponseEntity.noContent().build();
    }

}
