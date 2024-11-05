package com.example.aerolinea.service;

import com.example.aerolinea.dto.UserDto;
import com.example.aerolinea.dto.UserMapper;
import com.example.aerolinea.entity.User;

import com.example.aerolinea.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.INSTANCE.toUser(userDto);
        return userMapper.INSTANCE.toUserDTOID(userRepository.save(user));
    }

    @Override
    public Optional<UserDto> searchUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toUserDTOID);
    }

    @Override
    public List<UserDto> searchUserByName(String name) {
        List<User> users = userRepository.findbyusername(name);
        return toListUserDTO(users);
    }

    @Override
    public Optional<UserDto> searchUserByEmail(String email) {
        return userRepository.findbyEmail(email).map(userMapper::toUserDTOID);
    }

    @Override
    public Optional<UserDto> searchUserByUsername(String username) {
        return userRepository.findbyname(username).map(userMapper::toUserDTOID);
    }

    @Override
    public List<UserDto> searchUsers() {
        List<User> users = userRepository.findAll();
        return toListUserDTO(users);
    }

    @Override
    public List<UserDto> searchUserbyIds(List<Long> ids) {
        List<User> users = userRepository.findbyidIn(ids);
        return toListUserDTO(users);
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        return userRepository.findById(id).map(oldUser ->{
            oldUser.setEmail(userDto.email());
            oldUser.setUsername(userDto.username());
            oldUser.setPassword(userDto.password());
            oldUser.setFirstName(userDto.firstName());
            oldUser.setLastName(userDto.lastName());
            oldUser.setAddress(userDto.address());
            oldUser.setDob(userDto.dob());
            oldUser.setPhone(userDto.phone());
            return userMapper.INSTANCE.toUserDTOID(userRepository.save(oldUser));
        });
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private List<UserDto> toListUserDTO(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(userMapper.INSTANCE.toUserDTOID(user));
        }
        return userDtos;
    }
}