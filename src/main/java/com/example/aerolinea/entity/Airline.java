package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Airlines")
@Getter
@Setter
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String codeAirline;

    @Column(nullable = false)
    private String countryOrigin;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
}
