package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reservations")
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private User user;

    private LocalDateTime reservationDate;
    private int numberPassengers;

    @ManyToOne
    @JoinColumn(name = "id_Passenger")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id") // Columna FK en la tabla `Booking`
    private Flight flight;
}
