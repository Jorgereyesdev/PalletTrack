package com.riwi.PalletTrack.application.dtos.response;

import com.riwi.PalletTrack.domain.model.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String role;

    public AuthResponse(String token, UserRole role) {
        this.token = token;
        this.role = String.valueOf(role);
    }
}