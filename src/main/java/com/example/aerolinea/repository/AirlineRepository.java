package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    List<Airline> findByName(String airlineName);
    List<Airline> findByIdIn(Collection<Long> ids);
}
