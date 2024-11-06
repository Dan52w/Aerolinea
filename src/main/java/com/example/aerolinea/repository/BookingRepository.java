package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPassenger_FirstName(String bookingName);
    List<Booking> findByidIn(Collection<Long> ids);
}
