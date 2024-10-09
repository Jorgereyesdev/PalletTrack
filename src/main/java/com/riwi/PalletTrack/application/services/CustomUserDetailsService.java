package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.UserEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper.UserMapper;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Buscar el usuario por su email desde la base de datos
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado con el email: " + email);
                    return new UsernameNotFoundException("User not found");
                });

        System.out.println("Usuario encontrado: " + userEntity.getEmail());

        // Retornar los detalles del usuario para autenticación
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),  // Utilizar el email como el nombre de usuario
                userEntity.getPassword(),  // Contraseña
                List.of(() -> userEntity.getRole().name())  // Roles
        );
    }
}
