package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    List<Passenger> findByFirstName(String passengertName);
    Optional<Passenger> findByIdentification(int identification);
    List<Passenger> findByidIn(Collection<Long> ids);
}
