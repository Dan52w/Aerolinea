package com.example.aerolinea.service;

import com.example.aerolinea.dto.request.UserDto;
import com.example.aerolinea.dto.response.UserDtoGet;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    Optional<UserDtoGet> searchUserById(Long id);
    Optional<UserDtoGet> searchUserByEmail(String email);
    Optional<UserDtoGet> searchUserByUsername(String username);
    List<UserDtoGet> searchUsers();
    List<UserDtoGet> searchUserbyIds(List<Long> ids);
    Optional<UserDto> updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
}
