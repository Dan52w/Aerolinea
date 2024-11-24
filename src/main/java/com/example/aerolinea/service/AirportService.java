package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.AirportDto;
import com.example.aerolinea.dto.response.AirportDtoGet;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    AirportDto saveAirport(AirportDto airportDto);
    Optional<AirportDto> searchAirportById(Long id);
    List<AirportDtoGet> searchAirportByName(String name);
    List<AirportDtoGet> searchAirports();
    List<AirportDtoGet> searchAirportByIds(List<Long> ids);
    Optional<AirportDto> updateAirport(Long id, AirportDto airportDto);
    void deleteAirport(Long id);
}
