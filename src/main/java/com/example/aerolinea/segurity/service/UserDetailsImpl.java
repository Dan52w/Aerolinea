package com.example.aerolinea.segurity.service;

import com.example.aerolinea.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private LocalDate dob;
    private String firstName;
    private String phone;
    private String lastName;
    private String address;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password, LocalDate dob, String firstName, String phone, String lastName, String address, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.firstName = firstName;
        this.phone = phone;
        this.lastName = lastName;
        this.address = address;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getDob(),
                user.getFirstName(),
                user.getPhone(),
                user.getLastName(),
                user.getAddress(),
                authorities);
    }
}
