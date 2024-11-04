package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    List<Airline> finbyname(String airlineName);
    List<Airline> finbyidIn(List<Long> ids);
}
