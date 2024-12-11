package com.example.aerolinea.api;

import com.example.aerolinea.dto.request.FlightDto;
import com.example.aerolinea.dto.response.FlightDtoGet;
import com.example.aerolinea.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightDtoGet>> getAllFlights() {
        return ResponseEntity.ok(flightService.searchFlights());
    }

    @GetMapping("/origin/{origin}")
    public ResponseEntity<List<FlightDtoGet>> getFlight(@PathVariable String origin) {
        return ResponseEntity.ok(flightService.searchFlightByOrigin(origin));
    }

    @GetMapping("/destiny/{destiny}")
    public ResponseEntity<List<FlightDtoGet>> getFlightByDestiny(@PathVariable String destiny) {
        return ResponseEntity.ok(flightService.searchFlightByDestination(destiny));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        return flightService.searchFlightById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/{origin}/{destiny}")
    public ResponseEntity<List<FlightDtoGet>> getFlightByOriginAndDestiny(@PathVariable String origin, @PathVariable String destiny) {
        return ResponseEntity.ok(flightService.searchFlightByOriginAndDestination(origin, destiny));
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
