package com.example.aerolinea.dto.response;

import com.example.aerolinea.dto.request.FlightDto;

import java.util.List;

public record AirlineDtoGet (Long id,
                             String name,
                             String codeAirline,
                             String countryOrigin,
                             List<FlightDto> flights) {
}
