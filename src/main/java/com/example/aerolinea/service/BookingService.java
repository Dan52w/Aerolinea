package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.BookingDto;
import com.example.aerolinea.dto.response.BookingDtoGet;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDto saveBook(BookingDto bookingDto);
    Optional<BookingDto> searchBookById(Long id);
    List<BookingDtoGet> searchBookByName(String name);
    List<BookingDtoGet> searchReservations();
    List<BookingDtoGet> searchBookByIds(List<Long> ids);
    Optional<BookingDto> updateBook(Long id, BookingDto bookingDto);
    void deleteBook(Long id);
}
