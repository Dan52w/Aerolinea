package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Flight toFlight(FlightDto flightDto);

    Flight toFlightID(FlightDto flightDto);

    @Mapping(source = "id", target = "id", ignore = true)
    FlightDto toFlightDTO(Flight flight);

    FlightDto toFlightDTOID(Flight flight);
}
