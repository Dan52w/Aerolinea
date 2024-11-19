package com.example.aerolinea.api;

import com.example.aerolinea.dto.AirlineDto;
import com.example.aerolinea.service.AirlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/airline")
public class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping()
    public ResponseEntity<List<AirlineDto>> getAllAirlines() {
        return ResponseEntity.ok(airlineService.searchAirlines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getAirlineById(@PathVariable Long id) {
        return  airlineService.searchAirlineById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<AirlineDto> createAirline(@RequestBody AirlineDto airlineDto) {
        return createNewAirline(airlineDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirlineDto> updateAirline(@PathVariable Long id, @RequestBody AirlineDto airlineDto) {
        Optional<AirlineDto> airlineDtoUpdate = airlineService.UpdateAirline(id, airlineDto);
        return airlineDtoUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                    return createNewAirline(airlineDto);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AirlineDto> deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<AirlineDto> createNewAirline(AirlineDto airlineDto) {
        AirlineDto newAirline = airlineService.saveAirline(airlineDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAirline.id())
                .toUri();
        return ResponseEntity.created(location).body(newAirline);
    }
}
