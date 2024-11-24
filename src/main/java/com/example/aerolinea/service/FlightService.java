package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.FlightDto;
import com.example.aerolinea.dto.response.FlightDtoGet;

import java.util.List;
import java.util.Optional;

public interface FlightService {
    FlightDto saveFlight(FlightDto flightDto);
    Optional<FlightDto> searchFlightById(Long id);
    List<FlightDtoGet> searchFlightByOrigin(String name);
    List<FlightDtoGet> searchFlightByDestination(String name);
    List<FlightDtoGet> searchFlightByOriginAndDestination(String origin, String destination);
    List<FlightDtoGet> searchFlights();
    List<FlightDtoGet> searchFlightByIds(List<Long> ids);
    Optional<FlightDto> updateFlight(Long id,FlightDto flightDto);
    void deleteFlight(Long id);
}
