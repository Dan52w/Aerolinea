package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.UserDto;
import com.example.aerolinea.dto.response.UserDtoGet;
import com.example.aerolinea.entity.User;
import com.example.aerolinea.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BookingMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    User toUser(UserDto userDto);

    User toUserID(UserDto userDto);

    UserDto toUserDTO(User user);

    UserDto toUserDTOID(User user);

    // Mapeo de User a UserDtoGet
    @Mapping(source = "reservations", target = "bookings", qualifiedByName = "mapReservationsToBookings")
    UserDtoGet toUserDtoGet(User user);

    // Método auxiliar para mapear reservas de tipo Booking a una lista de String
    @Named("mapReservationsToBookings")
    default List<String> mapReservationsToBookings(List<Booking> reservations) {
        // Aquí puedes modificar este mapeo para devolver el atributo que deseas (por ejemplo, un identificador o nombre de vuelo)
        return reservations.stream()
                .map(booking -> booking.getUser() != null ? "Flight: " + booking.getFlight().getId() + " With Destiny: " + booking.getFlight().getDestination() : null)
                .collect(Collectors.toList());
    }
}
