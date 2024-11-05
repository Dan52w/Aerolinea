package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Passengers")
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private int identification;
    private String email;
    private String phone;
    private String gender;
    private LocalDateTime flightdate;

    @OneToMany(mappedBy = "pasajeros")
    private List<Booking> Reservations;
}
