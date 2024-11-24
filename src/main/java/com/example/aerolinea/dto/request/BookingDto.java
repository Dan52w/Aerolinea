package com.example.aerolinea.dto.request;

import java.time.LocalDateTime;

public record BookingDto(Long id,
                         Long iduser,
                         LocalDateTime reservationDate,
                         int numberPassengers,
                         Long idflight,
                         Long idpassenger) {
}
