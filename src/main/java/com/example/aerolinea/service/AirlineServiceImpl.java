package com.example.aerolinea.service;

import com.example.aerolinea.dto.AirlineDto;
import com.example.aerolinea.dto.AirlineMapper;
import com.example.aerolinea.entity.Airline;
import com.example.aerolinea.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirlineServiceImpl implements AirlineService {
    private AirlineRepository airlineRepository;
    private AirlineMapper airlineMapper;

    public AirlineServiceImpl(AirlineRepository airlineRepository, AirlineMapper airlineMapper) {
        this.airlineRepository = airlineRepository;
        this.airlineMapper = airlineMapper;
    }

    @Override
    public AirlineDto saveAirline(AirlineDto airlineDto) {
        Airline airline = airlineMapper.INSTANCE.toAirline(airlineDto);
        return airlineMapper.toAirlineDTOID(airlineRepository.save(airline));
    }

    @Override
    public Optional<AirlineDto> searchAirlineById(Long id) {
        return airlineRepository.findById(id).map(airlineMapper::toAirlineDTOID);
    }

    @Override
    public List<AirlineDto> searchAirlineByName(String name) {
        List<Airline> airlines = airlineRepository.findByName(name);
        return toListAirlineDTO(airlines);
    }

    @Override
    public List<AirlineDto> searchAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return toListAirlineDTO(airlines);
    }

    @Override
    public List<AirlineDto> searchAirlineByIds(List<Long> ids) {
        List<Airline> airlines = airlineRepository.findByIdIn(ids);
        return toListAirlineDTO(airlines);
    }

    @Override
    public Optional<AirlineDto> UpdateAirline(Long id, AirlineDto airlineDto) {
        return airlineRepository.findById(id).map(oldAirline -> {
            oldAirline.setName(airlineDto.name());
            oldAirline.setCodeAirline(airlineDto.codeAirline());
            oldAirline.setCountryOrigin(airlineDto.countryOrigin());
            return airlineMapper.INSTANCE.toAirlineDTOID(airlineRepository.save(oldAirline));
        });
    }

    @Override
    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }

    private List<AirlineDto> toListAirlineDTO(List<Airline> airlines) {
        List<AirlineDto> airlineDtos = new ArrayList<>();
        for(Airline airline : airlines) {
            airlineDtos.add(airlineMapper.INSTANCE.toAirlineDTOID(airline));
        }
        return airlineDtos;
    }
}
