package com.example.aerolinea.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PassengerDtoGet(Long id,
                              String firstName,
                              String lastName,
                              int age,
                              int identification,
                              String email,
                              String phone,
                              String gender,
                              LocalDateTime flightdate,
                              List<String> reservations) {
}
