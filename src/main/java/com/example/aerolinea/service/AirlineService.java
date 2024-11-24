package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.AirlineDto;
import com.example.aerolinea.dto.response.AirlineDtoGet;

import java.util.List;
import java.util.Optional;

public interface AirlineService {
    AirlineDto saveAirline(AirlineDto airlineDto);
    Optional<AirlineDto> searchAirlineById(Long id);
    List<AirlineDtoGet> searchAirlineByName(String name);
    List<AirlineDtoGet> searchAirlines();
    List<AirlineDtoGet> searchAirlineByIds(List<Long> ids);
    Optional<AirlineDto> UpdateAirline(Long id, AirlineDto airlineDto);
    void deleteAirline(Long id);
}
