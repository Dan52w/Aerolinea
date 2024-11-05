package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Reservations")
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    private LocalDateTime reservationDate;
    private int numberPassengers;

    @ManyToOne
    @JoinColumn(name = "idPassenger")
    private Passenger passenger;

    @ManyToMany
    @JoinTable(
            name = "BookFlights",
            joinColumns = @JoinColumn(name = "idFlight",  referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "idBooking", referencedColumnName = "id")
    )
    private List<Flight> flights;
}
