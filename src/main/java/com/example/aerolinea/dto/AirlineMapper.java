package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirlineMapper {
    AirlineMapper INSTANCE = Mappers.getMapper(AirlineMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Airline toAirline(AirlineDto airlineDto);

    Airline toAirlineID(AirlineDto airlineDto);

    @Mapping(source = "id", target = "id", ignore = true)
    AirlineDto toAirlineDTO(Airline airline);

    AirlineDto toAirlineDTOID(Airline airline);
}
