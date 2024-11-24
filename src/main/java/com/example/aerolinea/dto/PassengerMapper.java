package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.PassengerDto;
import com.example.aerolinea.dto.response.PassengerDtoGet;
import com.example.aerolinea.entity.Booking;
import com.example.aerolinea.entity.Passenger;
import com.example.aerolinea.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BookingMapper.class, UserMapper.class}) // Asegúrate de incluir BookingMapper
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);

    // Mapeo de PassengerDto a Passenger
    @Mapping(source = "id", target = "id", ignore = true) // No se mapea el id ya que es generado automáticamente
    Passenger toPassenger(PassengerDto passengerDto);

    // Mapeo de Passenger a PassengerDto
    PassengerDto toPassengerDto(Passenger passenger);

    // Método auxiliar si deseas mapear reservas con más detalles
    // Este mapea la lista de reservas de Booking a BookingDtoGet
    @Mappings({
            @Mapping(source = "firstName", target = "firstName"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "reservations", target = "reservations", qualifiedByName = "mapReservations") // Mapea las reservas
    })
    PassengerDtoGet toPassengerDtoGetWithReservations(Passenger passenger);

    // Mapea las reservas de tipo Booking a una lista de nombres de usuario (String)
    @Named("mapReservations")
    default List<String> mapReservations(List<Booking> reservations) {
        return reservations.stream()
                .map(booking -> booking.getUser() != null ? "Flight: " + booking.getFlight().getId() + " With Destiny: " + booking.getFlight().getDestination() : null)
                .collect(Collectors.toList());
    }

    @Named("userToString")
    default String userToString(User user) {
        return user != null ? user.getFirstName() + " " + user.getLastName() : null;
    }
}
