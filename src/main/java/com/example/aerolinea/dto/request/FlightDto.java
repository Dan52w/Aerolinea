package com.example.aerolinea.dto.request;

import java.time.Duration;
import java.time.LocalDateTime;

public record FlightDto(Long id,
                        String origin,
                        String destination,
                        LocalDateTime departureDate,
                        LocalDateTime timeArrival,
                        Duration duration,
                        int ability,
                        Long airportOrigin,
                        Long airportDestiny,
                        Long airline) {
}
