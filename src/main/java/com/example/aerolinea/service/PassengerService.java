package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.PassengerDto;
import com.example.aerolinea.dto.response.PassengerDtoGet;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    PassengerDto savePassenger(PassengerDto passengerDto);
    Optional<PassengerDtoGet> searchPassengerById(Long id);
    Optional<PassengerDtoGet> searchPassengerByIdentification(int Identification);
    List<PassengerDtoGet> searchPassengers();
    List<PassengerDtoGet>searchPassengerByName(String name);
    Optional<PassengerDto> updatePassenger(Long id,PassengerDto passengerDto);
    void deletePassengers(Long id);
}
