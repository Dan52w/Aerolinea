package com.example.aerolinea.dto;

import java.time.LocalDateTime;

public record BookingDto(Long id,
                         LocalDateTime reservationDate,
                         int numberPassengers) {
}
