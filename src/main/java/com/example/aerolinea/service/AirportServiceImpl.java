package com.example.aerolinea.service;

import com.example.aerolinea.dto.AirportDto;
import com.example.aerolinea.dto.AirportMapper;
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
        Airport airport = airportMapper.INSTANCE.toAirport(airportDto);
        return airportMapper.INSTANCE.toAirportDTOID(airportRepository.save(airport));
    }

    @Override
    public Optional<AirportDto> searchAirportById(Long id) {
        return airportRepository.findById(id).map(airportMapper::toAirportDTOID);
    }

    @Override
    public List<AirportDto> searchAirportByName(String name) {
        List<Airport> airports = airportRepository.findByName(name);
        return toListAirportDTO(airports);
    }

    @Override
    public List<AirportDto> searchAirports() {
        List<Airport> airports = airportRepository.findAll();
        return toListAirportDTO(airports);
    }

    @Override
    public List<AirportDto> searchAirportByIds(List<Long> ids) {
        List<Airport> airports = airportRepository.findByidIn(ids);
        return toListAirportDTO(airports);
    }

    @Override
    public Optional<AirportDto> updateAirport(Long id, AirportDto airportDto) {
        return airportRepository.findById(id).map(oldAirport ->{
            oldAirport.setCity(airportDto.city());
            oldAirport.setCountry(airportDto.country());
            oldAirport.setName(airportDto.name());
            return airportMapper.INSTANCE.toAirportDTOID(airportRepository.save(oldAirport));
        });
    }

    @Override
    public void deleteAirport(Long id) {

    }

    private List<AirportDto> toListAirportDTO(List<Airport> airports) {
        List<AirportDto> airportDtos = new ArrayList<>();
        for (Airport airport : airports) {
            airportDtos.add(airportMapper.INSTANCE.toAirportDTOID(airport));
        }
        return airportDtos;
    }
}
