package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.BookingDto;
import com.example.aerolinea.dto.BookingMapper;
import com.example.aerolinea.dto.response.BookingDtoGet;
import com.example.aerolinea.entity.Booking;
import com.example.aerolinea.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDto saveBook(BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Override
    public Optional<BookingDto> searchBookById(Long id) {
        return bookingRepository.findById(id).map(bookingMapper::toBookingDto);
    }

    @Override
    public List<BookingDtoGet> searchBookByName(String name) {
        List<Booking> bookings = bookingRepository.findByPassenger_FirstName(name);
        return toListBookingDTO(bookings);
    }

    @Override
    public List<BookingDtoGet> searchReservations() {
        List<Booking> bookings = bookingRepository.findAll();
        return toListBookingDTO(bookings);
    }

    @Override
    public List<BookingDtoGet> searchBookByIds(List<Long> ids) {
        List<Booking> bookings = bookingRepository.findByidIn(ids);
        return toListBookingDTO(bookings);
    }

    @Override
    public Optional<BookingDto> updateBook(Long id, BookingDto bookingDto) {
        return bookingRepository.findById(id).map(oldBooking ->{
            oldBooking.setReservationDate(bookingDto.reservationDate());
            oldBooking.setNumberPassengers(bookingDto.numberPassengers());
            return bookingMapper.toBookingDto(bookingRepository.save(oldBooking));
        });
    }

    @Override
    public void deleteBook(Long id) {
        bookingRepository.deleteById(id);
    }

    private List<BookingDtoGet> toListBookingDTO(List<Booking> bookings) {
        List<BookingDtoGet> bookingDtos = new ArrayList<>();
        for(Booking booking : bookings) {
            bookingDtos.add(bookingMapper.toBookingDtoGet(booking));
        }
        return bookingDtos;
    }
}
