package com.example.aerolinea.dto;

import com.example.aerolinea.dto.request.BookingDto;
import com.example.aerolinea.dto.response.BookingDtoGet;
import com.example.aerolinea.entity.Booking;
import com.example.aerolinea.entity.Flight;
import com.example.aerolinea.entity.Passenger;
import com.example.aerolinea.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PassengerMapper.class, UserMapper.class})
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    // Mapeo de BookingDto a Booking
    @Mapping(source = "iduser", target = "user.id")  // Mapea iduser a user.id
    @Mapping(source = "idpassenger", target = "passenger.id")    // Si no se necesita mapear el pasajero completo
    @Mapping(source = "idflight", target = "flight.id")
    Booking toBooking(BookingDto bookingDto);

    // Mapeo de Booking a BookingDto
    @Mapping(source = "user.id", target = "iduser")  // Mapea user.id a iduser
    BookingDto toBookingDto(Booking booking);

    // Mapeo de Booking a BookingDtoGet
    @Mapping(source = "user", target = "user", qualifiedByName = "userToStringInBooking")  // Mapea el username de user a String
    @Mapping(source = "passenger", target = "passenger", qualifiedByName = "passengerToString")  // Mapea el nombre del pasajero
    @Mapping(source = "flight", target = "flight", qualifiedByName = "flightToString")
    BookingDtoGet toBookingDtoGet(Booking booking);

    // Método auxiliar para mapear el nombre de usuario
    @Named("userToStringInBooking")
    default String userToStringInBooking(User user) {
        return user != null ? user.getFirstName() + " " + user.getLastName() : null;
    }

    // Método auxiliar para mapear el nombre del pasajero
    @Named("passengerToString")
    default String passengerToString(Passenger passenger) {
        return passenger != null ? passenger.getFirstName() + " " + passenger.getLastName() : null;
    }

    @Named("flightToString")
    default String flightToString(Flight flight) {
        return flight != null ? "Origin: " + flight.getOrigin() + " Destiny: " + flight.getDestination(): null;  // Suponiendo que Flight tiene un atributo 'flightCode'
    }
}
