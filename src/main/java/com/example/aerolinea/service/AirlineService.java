package com.example.aerolinea.service;

import com.example.aerolinea.dto.AirlineDto;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    AirlineDto saveAirline(AirlineDto airlineDto);
    Optional<AirlineDto> searchAirlineById(Long id);
    List<AirlineDto> searchAirlineByName(String name);
    List<AirlineDto> searchAirlines();
    List<AirlineDto> searchAirlineByIds(List<Long> ids);
    Optional<AirlineDto> UpdateAirline(Long id, AirlineDto airlineDto);
    void deleteAirline(Long id);
}
