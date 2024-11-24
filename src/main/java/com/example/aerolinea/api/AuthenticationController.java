package com.example.aerolinea.api;

import com.example.aerolinea.dto.JwtResponse;
import com.example.aerolinea.dto.LoginRequest;
import com.example.aerolinea.dto.SignupRequest;
import com.example.aerolinea.entity.ERole;
import com.example.aerolinea.entity.Role;
import com.example.aerolinea.entity.User;
import com.example.aerolinea.repository.RoleRepository;
import com.example.aerolinea.repository.UserRepository;
import com.example.aerolinea.segurity.jwt.JwtUtil;
import com.example.aerolinea.segurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AuthenticationController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(),
                        loginRequest.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken= jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwtToken, "Bearer", userDetails.getUsername(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sRequest) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByUsername(sRequest.username())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "El usuario ya está en uso"));
        }
        if (userRepository.existsByEmail(sRequest.email())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "El correo electrónico ya está en uso!"));
        }
        // Buscar el role en la base de datos utilizando el enum ERole
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role no encontrado."));
        // Crear un conjunto de roles y agregar el role de usuario
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        // Crear el usuario
        User user = new User(
                null,
                sRequest.username(),
                passwordEncoder.encode(sRequest.password()),
                sRequest.firstName(),
                sRequest.lastName(),
                sRequest.email(),
                sRequest.phone(),
                sRequest.address(),
                sRequest.dob(),
                null,
                roles // Asignar los roles
        );
        // Guardar el usuario en la base de datos
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }
}
