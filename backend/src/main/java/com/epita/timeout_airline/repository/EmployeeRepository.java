package com.epita.timeout_airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epita.timeout_airline.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
