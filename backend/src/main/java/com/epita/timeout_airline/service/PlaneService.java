package com.epita.timeout_airline.service;

import com.epita.timeout_airline.model.Plane;
import com.epita.timeout_airline.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlaneService {

    private final PlaneRepository planeRepository;

    @Transactional
    public Plane registerPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    @Transactional(readOnly = true)
    public Optional<Plane> findById(Long id) {
        return planeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Plane> findAll() {
        return planeRepository.findAll();
    }

    @Transactional
    public Plane updatePlane(Long id, Plane planeDetails) {
        Plane plane = planeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plane DNE, id: " + id));

        plane.setBrand(planeDetails.getBrand());
        plane.setModel(planeDetails.getModel());
        plane.setManufactureYear(planeDetails.getManufactureYear());

        return planeRepository.save(plane);
    }

    @Transactional
    public void deletePlane(Long id) {
        planeRepository.deleteById(id);
    }

}
