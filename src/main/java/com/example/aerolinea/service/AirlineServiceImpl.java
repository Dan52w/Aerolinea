package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.AirlineDto;
import com.example.aerolinea.dto.AirlineMapper;
import com.example.aerolinea.dto.response.AirlineDtoGet;
import com.example.aerolinea.entity.Airline;
import com.example.aerolinea.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Airline airline = airlineMapper.toAirline(airlineDto);
        return airlineMapper.toAirlineDTO(airlineRepository.save(airline));
    }

    @Override
    public Optional<AirlineDto> searchAirlineById(Long id) {
        return airlineRepository.findById(id).map(airlineMapper::toAirlineDTO);
    }

    @Override
    public List<AirlineDtoGet> searchAirlineByName(String name) {
        List<Airline> airlines = airlineRepository.findByName(name);
        return airlines.stream()
                .map(airlineMapper::toAirlineDtoGet)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirlineDtoGet> searchAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return airlines.stream()
                .map(airlineMapper::toAirlineDtoGet)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirlineDtoGet> searchAirlineByIds(List<Long> ids) {
        List<Airline> airlines = airlineRepository.findByIdIn(ids);
        return airlines.stream()
                .map(airlineMapper::toAirlineDtoGet)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AirlineDto> UpdateAirline(Long id, AirlineDto airlineDto) {
        return airlineRepository.findById(id).map(oldAirline -> {
            oldAirline.setName(airlineDto.name());
            oldAirline.setCodeAirline(airlineDto.codeAirline());
            oldAirline.setCountryOrigin(airlineDto.countryOrigin());
            return airlineMapper.toAirlineDTO(airlineRepository.save(oldAirline));
        });
    }

    @Override
    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }

    private List<AirlineDtoGet> toListAirlineDTO(List<Airline> airlines) {
        List<AirlineDtoGet> airlineDtos = new ArrayList<>();
        for(Airline airline : airlines) {
            airlineDtos.add(airlineMapper.toAirlineDtoGet(airline));
        }
        return airlineDtos;
    }
}
