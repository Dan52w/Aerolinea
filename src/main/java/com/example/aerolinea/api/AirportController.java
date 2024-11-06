package com.example.aerolinea.api;

import com.example.aerolinea.dto.AirportDto;
import com.example.aerolinea.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping()
    public ResponseEntity<List<AirportDto>> getAllAirport() {
        return ResponseEntity.ok(airportService.searchAirports());
    }

    @GetMapping("/id")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable Long id) {
        return airportService.searchAirportById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airportDto) {
        return createNewAirport(airportDto);
    }

    @PutMapping("/id")
    public ResponseEntity<AirportDto> updateAirport(@PathVariable Long id, @RequestBody AirportDto airportDto) {
        Optional<AirportDto> airportDtoUpdate = airportService.updateAirport(id, airportDto);
        return airportDtoUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() ->{
                   return createNewAirport(airportDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<AirportDto> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AirportDto> createNewAirport(AirportDto airportDto) {
        AirportDto newAirport = airportService.saveAirport(airportDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAirport.id())
                .toUri();
        return ResponseEntity.created(location).body(newAirport);
    }
}
