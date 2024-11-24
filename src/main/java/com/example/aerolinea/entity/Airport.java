package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Airports")
@Getter
@Setter
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String country;

    @OneToMany(mappedBy = "airportOrigin")
    private List<Flight> flightsAsOrigin;

    @OneToMany(mappedBy = "airportDestiny")
    private List<Flight> flightsAsDestiny;
}
