package com.example.aerolinea.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Airlines")
@Getter
@Setter
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String codigoAerolinea;

    @Column(nullable = false)
    private String paisOrigen;

    @ManyToOne
    @JoinColumn(name = "idflight")
    private Flight flights;
}
