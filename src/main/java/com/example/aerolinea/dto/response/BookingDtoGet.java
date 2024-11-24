package com.example.aerolinea.dto.response;

import java.time.LocalDateTime;

public record BookingDtoGet(Long id,
                            String user,
                            LocalDateTime reservationDate,
                            int numberPassengers,
                            String passenger,
                            String flight) {
}
