package com.epita.timeout_airline.service;

import com.epita.timeout_airline.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.epita.timeout_airline.model.Employee;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Employee registerEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

}
