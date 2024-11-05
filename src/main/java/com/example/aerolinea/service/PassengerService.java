package com.example.aerolinea.service;

import com.example.aerolinea.dto.PassengerDto;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    PassengerDto savePassenger(PassengerDto passengerDto);
    Optional<PassengerDto> searchPassengerById(Long id);
    List<PassengerDto> searchPassengers();
    List<PassengerDto>searchPassengerByName(String name);
    List<PassengerDto> searchPassengerByIds(List<Long> ids);
    Optional<PassengerDto> updatePassenger(Long id,PassengerDto passengerDto);
    void deletePassengers(Long id);
}
