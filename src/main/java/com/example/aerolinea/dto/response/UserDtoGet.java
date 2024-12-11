package com.example.aerolinea.dto.response;

import java.time.LocalDate;
import java.util.List;

public record UserDtoGet(Long id,
                         String username,
                         String firstName,
                         String lastName,
                         String email,
                         String phone,
                         String address,
                         LocalDate dob,
                         List<String> bookings) {
}
