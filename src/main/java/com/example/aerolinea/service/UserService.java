package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.UserDto;
import com.example.aerolinea.dto.response.UserDtoGet;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    Optional<UserDto> searchUserById(Long id);
    Optional<UserDto> searchUserByEmail(String email);
    Optional<UserDto> searchUserByUsername(String username);
    List<UserDtoGet> searchUsers();
    List<UserDtoGet> searchUserbyIds(List<Long> ids);
    Optional<UserDto> updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
