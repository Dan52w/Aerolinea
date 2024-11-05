package com.example.aerolinea.dto;

import com.example.aerolinea.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id", ignore = true)
    User toUser(UserDto userDto);

    User toUserID(UserDto userDto);

    @Mapping(source = "id", target = "id", ignore = true)
    UserDto toUserDTO(User user);

    UserDto toUserDTOID(User user);
}
