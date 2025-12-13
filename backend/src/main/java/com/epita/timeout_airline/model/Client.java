package com.epita.timeout_airline.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="clients")
@EqualsAndHashCode(callSuper=true)
@PrimaryKeyJoinColumn(name="id")
public class Client extends User{

    @Column(nullable=false)
    private String passportNumber;

}
