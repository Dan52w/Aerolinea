package com.example.aerolinea.dto.request;

import java.time.LocalDateTime;

public record PassengerDto(Long id,
                           String firstName,
                           String lastName,
                           int age,
                           int identification,
                           String email,
                           String phone,
                           String gender,
                           LocalDateTime flightdate) {
}
