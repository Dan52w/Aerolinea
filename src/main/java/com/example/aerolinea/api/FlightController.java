package com.example.aerolinea.api;

import com.example.aerolinea.dto.FlightDto;
import com.example.aerolinea.entity.Flight;
import com.example.aerolinea.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.searchFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        return flightService.searchFlightById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) {
        Optional<FlightDto> updateFlight = flightService.updateFlight(id, flightDto);
        return updateFlight.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createFlight(flightDto);
                });
    }

    @PostMapping()
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        return createNewFlight(flightDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDto> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
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
