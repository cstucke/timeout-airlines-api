package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="employees")
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name="id")
public class Employee extends User {

    @Column(nullable=false, unique=true)
    private Long employeeNumber;
    private String profession;
    private String title;

}
