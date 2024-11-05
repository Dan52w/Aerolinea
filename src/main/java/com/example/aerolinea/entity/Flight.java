package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private LocalDateTime duration;
    private int ability;

    @OneToMany(mappedBy = "fligth")
    private List<Airport> airports;

    @OneToMany(mappedBy = "flight")
    private List<Airline> airlines;

    @ManyToMany
    @JoinTable(
            name = "BookFlights",
            joinColumns = @JoinColumn(name = "idFlight",  referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idBooking", referencedColumnName = "id")
    )
    private List<Booking> Reservations;
}
