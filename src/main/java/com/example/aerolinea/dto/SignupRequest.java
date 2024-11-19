package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Role;

import java.time.LocalDate;
import java.util.Set;

public record SignupRequest(String username,
                            String password,
                            String firstName,
                            String lastName,
                            String email,
                            String phone,
                            String address,
                            LocalDate dob,
                            Set<Role> roles) {
}
