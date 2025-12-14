package com.epita.timeout_airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.epita.timeout_airline.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
