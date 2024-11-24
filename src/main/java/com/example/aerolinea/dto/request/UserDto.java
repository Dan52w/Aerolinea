package com.example.aerolinea.dto.request;

import java.time.LocalDate;

public record UserDto(Long id,
                      String username,
                      String password,
                      String firstName,
                      String lastName,
                      String email,
                      String phone,
                      String address,
                      LocalDate dob) {
}
