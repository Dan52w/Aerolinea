package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findbyname(String passengertName);
    List<Passenger> findbyidIn(Collection<Long> ids);
}
