package com.epita.timeout_airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epita.timeout_airline.model.Plane;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

}
