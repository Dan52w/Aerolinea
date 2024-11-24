package com.example.aerolinea.dto.request;

public record AirlineDto(Long id,
                         String name,
                         String codeAirline,
                         String countryOrigin) {
}
