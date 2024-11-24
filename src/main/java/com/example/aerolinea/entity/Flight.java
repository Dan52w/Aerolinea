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

    @ManyToOne
    @JoinColumn(name = "id_airportOrigin") // Clave foránea para aeropuerto de origen
    private Airport airportOrigin;

    @ManyToOne
    @JoinColumn(name = "id_airportDestiny") // Clave foránea para aeropuerto de destino
    private Airport airportDestiny;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @OneToMany(mappedBy = "flight")
    private List<Booking> Reservations;
}
