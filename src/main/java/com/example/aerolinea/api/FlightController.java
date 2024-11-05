package com.example.aerolinea.api;

import com.example.aerolinea.dto.FlightDto;
import com.example.aerolinea.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.searchFlights());
    }

    @GetMapping("/id")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        return flightService.searchFlightById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        return createNewFlight(flightDto);
    }

    private ResponseEntity<FlightDto> createNewFlight(FlightDto flightDto) {
        FlightDto newFlight = flightService.saveFlight(flightDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newFlight.id())
                .toUri();
        return ResponseEntity.created(location).body(newFlight);
    }
}
