package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "idflight")
    private Flight flights;
}
