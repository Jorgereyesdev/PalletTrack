package com.riwi.PalletTrack.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
}
