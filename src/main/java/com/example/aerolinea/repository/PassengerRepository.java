package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Booking;
import com.example.aerolinea.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByFirstName(String passengertName);
    List<Passenger> findByidIn(Collection<Long> ids);
}
