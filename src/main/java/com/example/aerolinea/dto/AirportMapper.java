package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.AirportDto;
import com.example.aerolinea.dto.response.AirportDtoGet;
import com.example.aerolinea.dto.response.FlightDtoGet;
import com.example.aerolinea.entity.Airport;
import com.example.aerolinea.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {FlightMapper.class})
public interface AirportMapper {
    AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

    // Convertir de AirportDto a entidad Airport (Guardar/Actualizar)
    @Mapping(source = "id", target = "id", ignore = true)
    Airport toAirport(AirportDto airportDto);

    // Convertir de entidad Airport a AirportDto (respuesta básica)
    AirportDto toAirportDTO(Airport airport);

    // Convertir de entidad Airport a AirportDtoGet (respuesta detallada con vuelos origen y destino)
    @Mapping(source = "flightsAsOrigin", target = "flightsAsOrigin", qualifiedByName = "mapFlightsToDtoGet")
    @Mapping(source = "flightsAsDestiny", target = "flightsAsDestiny", qualifiedByName = "mapFlightsToDtoGet")
    AirportDtoGet toAirportDtoGet(Airport airport);

    // Método auxiliar para mapear una lista de Flight a FlightDtoGet
    @Named("mapFlightsToDtoGet")
    default List<FlightDtoGet> mapFlightsToDtoGet(List<Flight> flights) {
        return flights.stream()
                .map(FlightMapper.INSTANCE::toFlightDtoGet) // Usar FlightMapper para mapear vuelos
                .collect(Collectors.toList());
    }
}