package com.example.aerolinea.service;

import com.example.aerolinea.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDto saveBook(BookingDto bookingDto);
    Optional<BookingDto> searchBookById(Long id);
    List<BookingDto> searchBookByName(String name);
    List<BookingDto> searchReservations();
    List<BookingDto> searchBookByIds(List<Long> ids);
    Optional<BookingDto> updateBook(Long id, BookingDto bookingDto);
    void deleteBook(Long id);
}
