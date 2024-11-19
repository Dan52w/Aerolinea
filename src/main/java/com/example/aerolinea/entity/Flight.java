package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights")
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime timeArrival;
    private Duration duration;
    private int ability;

    @ManyToMany
    @JoinTable(
            name = "flight_airport",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    private List<Airport> airports;

    @JoinColumn(name = "airline_id") // Columna FK en la tabla `flight`
    private Airline airline;

    @OneToMany(mappedBy = "flight")
    private List<Booking> Reservations;
}
