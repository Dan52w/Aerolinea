package com.example.aerolinea.dto.response;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public record FlightDtoGet(Long id,
                           String origin,
                           String destination,
                           LocalDateTime departureDate,
                           LocalDateTime timeArrival,
                           Duration duration,
                           int ability,
                           String idairline,
                           String idairportOrigin,
                           String idairportDestiny) {
}
