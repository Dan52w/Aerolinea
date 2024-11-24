package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.AirlineDto;
import com.example.aerolinea.dto.request.FlightDto;
import com.example.aerolinea.dto.response.AirlineDtoGet;
import com.example.aerolinea.entity.Airline;
import com.example.aerolinea.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",  uses = {FlightMapper.class})
public interface AirlineMapper {
    AirlineMapper INSTANCE = Mappers.getMapper(AirlineMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Airline toAirline(AirlineDto airlineDto);

    AirlineDto toAirlineDTO(Airline airline);

    @Mapping(source = "flights", target = "flights")
    AirlineDtoGet toAirlineDtoGet(Airline airline);

    // Método auxiliar para mapear la lista de vuelos a FlightDto
    @Named("mapFlightsToDto")
    default List<FlightDto> mapFlightsToDto(List<Flight> flights) {
        return flights.stream()
                .map(flight -> FlightMapper.INSTANCE.toFlightDto(flight))
                .collect(Collectors.toList());
    }
}
