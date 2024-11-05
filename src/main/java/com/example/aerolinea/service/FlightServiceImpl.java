package com.example.aerolinea.service;

import com.example.aerolinea.dto.FlightDto;
import com.example.aerolinea.dto.FlightMapper;
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
        Flight flight = flightMapper.INSTANCE.toFlight(flightDto);
        return flightMapper.INSTANCE.toFlightDTOID(flightRepository.save(flight));
    }

    @Override
    public Optional<FlightDto> searchFlightById(Long id) {
        return flightRepository.findById(id).map(flightMapper::toFlightDTOID);
    }

    @Override
    public List<FlightDto> searchFlightByName(String name) {
        List<Flight> flights = flightRepository.findbyname(name);
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDto> searchFlights() {
        List<Flight> flights = flightRepository.findAll();
        return toListFlightDTO(flights);
    }

    @Override
    public List<FlightDto> searchFlightByIds(List<Long> ids) {
        List<Flight> flights = flightRepository.findbyidIn(ids);
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
            return flightMapper.INSTANCE.toFlightDTOID(flightRepository.save(oldFlight));
        });
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    private List<FlightDto> toListFlightDTO(List<Flight> flights) {
        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightDto = flightMapper.INSTANCE.toFlightDTOID(flight);
        }
        return flightDtos;
    }
}
