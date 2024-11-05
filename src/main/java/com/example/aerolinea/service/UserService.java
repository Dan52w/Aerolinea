package com.example.aerolinea.service;

import com.example.aerolinea.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    Optional<UserDto> searchUserById(Long id);
    List<UserDto> searchUserByName(String name);
    Optional<UserDto> searchUserByEmail(String email);
    Optional<UserDto> searchUserByUsername(String username);
    List<UserDto> searchUsers();
    List<UserDto> searchUserbyIds(List<Long> ids);
    Optional<UserDto> updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
