package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Passenger toPassenger(PassengerDto passengerDto);

    Passenger toPassengerID(PassengerDto passengerDto);

    @Mapping(source = "id", target = "id", ignore = true)
    PassengerDto toPassengerDTO(Passenger passenger);

    PassengerDto toPassengerDTOID(Passenger passenger);
}
