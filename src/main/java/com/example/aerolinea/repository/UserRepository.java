package com.example.aerolinea.repository;

import com.example.aerolinea.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findbyidIn(Collection<Long> ids);
    List<User> findbyusername(String username);
    Optional<User> findbyname(String userName);
    Optional<User> findbyEmail(String email);
    Boolean existsbyname(String username);
    Boolean existsbyEmail(String email);
}
