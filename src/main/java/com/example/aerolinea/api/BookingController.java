package com.example.aerolinea.api;

import com.example.aerolinea.dto.BookingDto;
import com.example.aerolinea.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping()
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.searchReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        return bookingService.searchBookById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        return createNewBooking(bookingDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto bookingDto) {
        Optional<BookingDto>  bookingUpdate = bookingService.searchBookById(id);
        return bookingUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewBooking(bookingDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingDto> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<BookingDto> createNewBooking(BookingDto bookingDto) {
        BookingDto newBooking = bookingService.saveBook(bookingDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBooking)
                .toUri();
        return ResponseEntity.created(location).body(newBooking);
    }

}
