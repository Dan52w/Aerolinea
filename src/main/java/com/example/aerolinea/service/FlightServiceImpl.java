package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.FlightDto;
import com.example.aerolinea.dto.FlightMapper;
import com.example.aerolinea.dto.response.FlightDtoGet;
import com.example.aerolinea.entity.Flight;
import com.example.aerolinea.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDto saveFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toFlightSave(flightDto);
        return flightMapper.toFlightDto(flightRepository.save(flight));
    }

    @Override
    public Optional<FlightDto> searchFlightById(Long id) {
        return flightRepository.findById(id).map(flightMapper::toFlightDto);
    }

    @Override
    public List<FlightDtoGet> searchFlightByOrigin(String name) {
        List<Flight> flights = flightRepository.findByOrigin(name);
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDtoGet> searchFlightByDestination(String name) {
        List<Flight> flights = flightRepository.findByDestination(name);
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDtoGet> searchFlightByOriginAndDestination(String origin, String destination) {
        List<Flight> flights = flightRepository.findByOriginAndDestination(origin, destination);
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDtoGet> searchFlights() {
        List<Flight> flights = flightRepository.findAll();
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDtoGet> searchFlightByIds(List<Long> ids) {
        List<Flight> flights = flightRepository.findByidIn(ids);
        return toListFlightDTO(flights);
    }

    @Override
    public Optional<FlightDto> updateFlight(Long id, FlightDto flightDto) {
        return flightRepository.findById(id).map(oldFlight ->{
            oldFlight.setAbility(flightDto.ability());
            oldFlight.setDuration(flightDto.duration());
            oldFlight.setDestination(flightDto.destination());
            oldFlight.setOrigin(flightDto.origin());
            oldFlight.setTimeArrival(flightDto.timeArrival());
            oldFlight.setDepartureDate(flightDto.departureDate());
            return flightMapper.toFlightDto(flightRepository.save(oldFlight));
        });
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    private List<FlightDtoGet> toListFlightDTO(List<Flight> flights) {
        List<FlightDtoGet> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            flightDtos.add(flightMapper.toFlightDtoGet(flight));
        }
        return flightDtos;
    }
}
