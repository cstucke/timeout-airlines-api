package com.epita.timeout_airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epita.timeout_airline.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
