package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.dtos.request.AuthRequest;
import com.riwi.PalletTrack.application.dtos.request.UserRequest;
import com.riwi.PalletTrack.application.dtos.response.AuthResponse;
import com.riwi.PalletTrack.application.dtos.response.RegisterUserResponse; // Importa tu nuevo DTO
import com.riwi.PalletTrack.application.infrastructure.jwt.JwtUtil;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.UserEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.UserRepository;
import com.riwi.PalletTrack.domain.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // Método para registrar un nuevo usuario
    public RegisterUserResponse register(UserRequest userRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(UserRole.valueOf(userRequest.getRole()));

        UserEntity savedUser = userRepository.save(user);
        return new RegisterUserResponse(savedUser.getEmail(), savedUser.getRole().name()); // Devuelve el nuevo DTO
    }

    // Método para iniciar sesión
    public AuthResponse login(AuthRequest authRequest) {
        UserEntity user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(String.valueOf(user));
        return new AuthResponse(token, user.getRole());
    }
}
