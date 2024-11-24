package com.example.aerolinea.dto.response;

import java.util.List;

public record AirportDtoGet(Long id,
                            String name,
                            String city,
                            String country,
                            List<FlightDtoGet> flightsAsOrigin,
                            List<FlightDtoGet> flightsAsDestiny) {
}
