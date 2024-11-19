package com.example.aerolinea.service;

import com.example.aerolinea.dto.BookingDto;
import com.example.aerolinea.dto.BookingMapper;
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
        Booking booking = bookingMapper.INSTANCE.toBooking(bookingDto);
        return bookingMapper.INSTANCE.toBookingDTOID(bookingRepository.save(booking));
    }

    @Override
    public Optional<BookingDto> searchBookById(Long id) {
        return bookingRepository.findById(id).map(bookingMapper::toBookingDTOID);
    }

    @Override
    public List<BookingDto> searchBookByName(String name) {
        List<Booking> bookings = bookingRepository.findByPassenger_FirstName(name);
        return toListBookingDTO(bookings);
    }

    @Override
    public List<BookingDto> searchReservations() {
        List<Booking> bookings = bookingRepository.findAll();
        return toListBookingDTO(bookings);
    }

    @Override
    public List<BookingDto> searchBookByIds(List<Long> ids) {
        List<Booking> bookings = bookingRepository.findByidIn(ids);
        return toListBookingDTO(bookings);
    }

    @Override
    public Optional<BookingDto> updateBook(Long id, BookingDto bookingDto) {
        return bookingRepository.findById(id).map(oldBooking ->{
            oldBooking.setReservationDate(bookingDto.reservationDate());
            oldBooking.setNumberPassengers(bookingDto.numberPassengers());
            return bookingMapper.INSTANCE.toBookingDTOID(bookingRepository.save(oldBooking));
        });
    }

    @Override
    public void deleteBook(Long id) {
        bookingRepository.deleteById(id);
    }

    private List<BookingDto> toListBookingDTO(List<Booking> bookings) {
        List<BookingDto> bookingDtos = new ArrayList<>();
        for(Booking booking : bookings) {
            bookingDtos.add(bookingMapper.INSTANCE.toBookingDTOID(booking));
        }
        return bookingDtos;
    }
}
