package com.example.aerolinea.repository;

import com.example.aerolinea.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findbyname(String bookingName);
    List<Booking> findbyidIn(Collection<Long> ids);
}
