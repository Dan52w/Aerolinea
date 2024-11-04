package com.example.aerolinea.dto;

import com.example.aerolinea.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    Booking toBooking(BookingDto bookingDto);

    Booking toBookingID(BookingDto bookingDto);

    @Mapping(source = "id", target = "id", ignore = true)
    BookingDto toBookingDTO(Booking booking);

    BookingDto toBookingDTOID(Booking booking);
}
