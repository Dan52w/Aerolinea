package com.example.aerolinea.service;

import com.example.aerolinea.dto.AirportDto;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    AirportDto saveAirport(AirportDto airportDto);
    Optional<AirportDto> searchAirportById(Long id);
    List<AirportDto> searchAirportByName(String name);
    List<AirportDto> searchAirports();
    List<AirportDto> searchAirportByIds(List<Long> ids);
    Optional<AirportDto> updateAirport(Long id, AirportDto airportDto);
    void deleteAirport(Long id);
}
