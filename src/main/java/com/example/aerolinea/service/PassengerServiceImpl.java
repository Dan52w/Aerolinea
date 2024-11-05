package com.example.aerolinea.service;

import com.example.aerolinea.dto.PassengerDto;
import com.example.aerolinea.dto.PassengerMapper;
import com.example.aerolinea.entity.Passenger;
import com.example.aerolinea.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl  implements PassengerService{
    private PassengerRepository passengerRepository;
    private PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDto savePassenger(PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.INSTANCE.toPassenger(passengerDto);
        return passengerMapper.INSTANCE.toPassengerDTOID(passengerRepository.save(passenger));
    }

    @Override
    public Optional<PassengerDto> searchPassengerById(Long id) {
        return passengerRepository.findById(id).map(passengerMapper::toPassengerDTOID);
    }

    @Override
    public List<PassengerDto> searchPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return toListPassengerDTO(passengers);
    }

    @Override
    public List<PassengerDto> searchPassengerByName(String name) {
        List<Passenger> passengers = passengerRepository.findbyname(name);
        return toListPassengerDTO(passengers);
    }

    @Override
    public List<PassengerDto> searchPassengerByIds(List<Long> ids) {
        List<Passenger> passengers = passengerRepository.findbyidIn(ids);
        return toListPassengerDTO(passengers);
    }

    @Override
    public Optional<PassengerDto> updatePassenger(Long id, PassengerDto passengerDto) {
        return passengerRepository.findById(id).map(oldPassenger ->{
            oldPassenger.setAge(passengerDto.age());
            oldPassenger.setEmail(passengerDto.email());
            oldPassenger.setGender(passengerDto.gender());
            oldPassenger.setFlightdate(passengerDto.flightdate());
            oldPassenger.setFirstName(passengerDto.firstName());
            oldPassenger.setLastName(passengerDto.lastName());
            oldPassenger.setIdentification(passengerDto.identification());
            oldPassenger.setPhone(passengerDto.phone());
            return passengerMapper.INSTANCE.toPassengerDTOID(passengerRepository.save(oldPassenger));
        });
    }

    @Override
    public void deletePassengers(Long id) {
        passengerRepository.deleteById(id);
    }

    private List<PassengerDto> toListPassengerDTO(List<Passenger> passengers) {
        List<PassengerDto> passengerDtos = new ArrayList<>();
        for (Passenger passenger : passengers) {
            passengerDtos.add(passengerMapper.INSTANCE.toPassengerDTOID(passenger));
        }
        return passengerDtos;
    }
}
