package com.epita.timeout_airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epita.timeout_airline.model.Airport;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
