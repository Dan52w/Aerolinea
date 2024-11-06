package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByName(String airportName);
    List<Airport> findByidIn(Collection<Long> ids);
}
