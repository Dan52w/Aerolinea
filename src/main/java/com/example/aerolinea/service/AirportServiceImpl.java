package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.AirportDto;
import com.example.aerolinea.dto.AirportMapper;
import com.example.aerolinea.dto.response.AirportDtoGet;
import com.example.aerolinea.entity.Airport;
import com.example.aerolinea.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    private AirportRepository airportRepository;
    private AirportMapper airportMapper;

    public AirportServiceImpl(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public AirportDto saveAirport(AirportDto airportDto) {
        Airport airport = airportMapper.toAirport(airportDto);
        return airportMapper.toAirportDTO(airportRepository.save(airport));
    }

    @Override
    public Optional<AirportDto> searchAirportById(Long id) {
        return airportRepository.findById(id).map(airportMapper::toAirportDTO);
    }

    @Override
    public List<AirportDtoGet> searchAirportByName(String name) {
        List<Airport> airports = airportRepository.findByName(name);
        return toListAirportDTO(airports);
    }

    @Override
    public List<AirportDtoGet> searchAirports() {
        List<Airport> airports = airportRepository.findAll();
        return toListAirportDTO(airports);
    }

    @Override
    public List<AirportDtoGet> searchAirportByIds(List<Long> ids) {
        List<Airport> airports = airportRepository.findByidIn(ids);
        return toListAirportDTO(airports);
    }

    @Override
    public Optional<AirportDto> updateAirport(Long id, AirportDto airportDto) {
        return airportRepository.findById(id).map(oldAirport ->{
            oldAirport.setCity(airportDto.city());
            oldAirport.setCountry(airportDto.country());
            oldAirport.setName(airportDto.name());
            return airportMapper.toAirportDTO(airportRepository.save(oldAirport));
        });
    }

    @Override
    public void deleteAirport(Long id) {

    }

    private List<AirportDtoGet> toListAirportDTO(List<Airport> airports) {
        List<AirportDtoGet> airportDtos = new ArrayList<>();
        for (Airport airport : airports) {
            airportDtos.add(airportMapper.toAirportDtoGet(airport));
        }
        return airportDtos;
    }
}
