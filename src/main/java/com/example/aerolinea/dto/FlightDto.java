package com.example.aerolinea.dto;

import java.time.LocalDateTime;

public record FlightDto(Long id,
                        String origin,
                        String destination,
                        LocalDateTime departureDate,
                        LocalDateTime timeArrival,
                        LocalDateTime duration,
                        int ability) {
}
