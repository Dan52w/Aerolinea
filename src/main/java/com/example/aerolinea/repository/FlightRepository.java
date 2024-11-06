package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOrigin(String flightName);
    List<Flight> findByidIn(Collection<Long> ids);
}
