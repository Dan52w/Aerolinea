package com.example.aerolinea.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    private String phone;
    private String address;
    private LocalDate dob;

    @OneToMany(mappedBy = "user")
    private List<Booking> Reservations;
}
