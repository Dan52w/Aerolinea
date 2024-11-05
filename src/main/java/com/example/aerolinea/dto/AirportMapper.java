package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirportMapper {
    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Airport toAirport(AirportDto airportDto);

    Airport toAirportID(AirportDto airportDto);

    @Mapping(source = "id", target = "id", ignore = true)
    AirportDto toAirportDTO(Airport airport);

    AirportDto toAirportDTOID(Airport airport);
}
