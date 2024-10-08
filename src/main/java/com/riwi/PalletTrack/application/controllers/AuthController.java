package com.riwi.PalletTrack.application.controllers;

import com.riwi.PalletTrack.application.dtos.request.AuthRequest;
import com.riwi.PalletTrack.application.dtos.request.UserRequest;
import com.riwi.PalletTrack.application.dtos.response.AuthResponse;
import com.riwi.PalletTrack.application.dtos.response.RegisterUserResponse;
import com.riwi.PalletTrack.application.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody UserRequest userRequest) {
        RegisterUserResponse registeredUser = authService.register(userRequest);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
