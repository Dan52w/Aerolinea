package com.example.aerolinea.api;

import com.example.aerolinea.dto.PassengerDto;
import com.example.aerolinea.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping()
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.searchPassengers());
    }

    @GetMapping("/id")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable Long id) {
        return passengerService.searchPassengerById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto passengerDto) {
        return createNewPassenger(passengerDto);
    }

    @PutMapping("/id")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable Long id, @RequestBody PassengerDto passengerDto) {
        Optional<PassengerDto> passengerUpdate = passengerService.updatePassenger(id, passengerDto);
        return passengerUpdate.map(c -> ResponseEntity.ok(c))
                .orElseGet(() -> {
                   return createNewPassenger(passengerDto);
                });
    }

    @DeleteMapping("/id")
    public ResponseEntity<PassengerDto> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassengers(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<PassengerDto> createNewPassenger(PassengerDto passengerDto) {
        PassengerDto newPassenger = passengerService.savePassenger(passengerDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPassenger.id())
                .toUri();
        return ResponseEntity.created(location).body(newPassenger);
    }
}
