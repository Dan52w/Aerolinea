package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.FlightDto;
import com.example.aerolinea.dto.response.FlightDtoGet;
import com.example.aerolinea.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    // Mapear de FlightDto (entrada) a Flight (entidad)
    @Mapping(source = "airportOrigin", target = "airportOrigin.id")
    @Mapping(source = "airportDestiny", target = "airportDestiny.id")
    @Mapping(source = "airline", target = "airline.id")
    @Mapping(source = "id", target = "id", ignore = true) // Ignorar ID al guardar
    Flight toFlightSave(FlightDto flightDto);

    // Mapear de Flight (entidad) a FlightDto (salida básica)
    @Mapping(source = "airportOrigin.id", target = "airportOrigin")
    @Mapping(source = "airportDestiny.id", target = "airportDestiny")
    @Mapping(source = "airline.id", target = "airline")
    FlightDto toFlightDto(Flight flight);

    // Mapear de Flight (entidad) a FlightDtoGet (salida completa para listar)
    @Mapping(source = "airline.name", target = "idairline")
    @Mapping(source = "airportOrigin.name", target = "idairportOrigin")
    @Mapping(source = "airportDestiny.name", target = "idairportDestiny")
    FlightDtoGet toFlightDtoGet(Flight flight);

    // Mapear de FlightDto (entrada básica) a Flight (solo con ID de referencias)
    @Mapping(source = "airportOrigin", target = "airportOrigin.id")
    @Mapping(source = "airportDestiny", target = "airportDestiny.id")
    @Mapping(source = "airline", target = "airline.id")
    @Mapping(source = "id", target = "id")
    Flight toFlightID(FlightDto flightDto);
}
