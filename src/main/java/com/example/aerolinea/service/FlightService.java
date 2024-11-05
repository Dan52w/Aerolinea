package com.example.aerolinea.service;

import com.example.aerolinea.dto.FlightDto;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDto saveFlight(FlightDto flightDto);
    Optional<FlightDto> searchFlightById(Long id);
    List<FlightDto> searchFlightByName(String name);
    List<FlightDto> searchFlights();
    List<FlightDto> searchFlightByIds(List<Long> ids);
    Optional<FlightDto> updateFlight(Long id,FlightDto flightDto);
    void deleteFlight(Long id);
}
