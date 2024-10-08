package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.LoadEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.UserEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.LoadRepository;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.PalletRepository;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.UserRepository;
import com.riwi.PalletTrack.domain.model.LoadStatus;
import com.riwi.PalletTrack.domain.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository; // Asumiendo que tienes un repositorio para UserEntity
    @Autowired
    private PalletRepository palletRepository; // Asumiendo que tienes un repositorio para PalletEntity
    @Autowired
    private LoadRepository loadRepository; // Asumiendo que tienes un repositorio para LoadEntity

    @Override
    public void run(String... args) {
        // Crear usuarios
        UserEntity admin = UserEntity.builder()
                .username("adminUser")
                .email("admin@example.com")
                .password("password123")
                .role(UserRole.ADMINISTRADOR)
                .build();

        UserEntity transportista = UserEntity.builder()
                .username("transportUser")
                .email("transport@example.com")
                .password("password123")
                .role(UserRole.TRANSPORTISTA)
                .build();

        userRepository.saveAll(Arrays.asList(admin, transportista));

        // Crear palets
        for (int i = 1; i <= 5; i++) {
            PalletEntity pallet = new PalletEntity();
            pallet.setCapacity(100.0 + (i * 50)); // Ejemplo de capacidad
            pallet.setLocation("Location " + i);
            pallet.setStatus("DISPONIBLE");
            palletRepository.save(pallet);
        }

        // Crear cargas
        LoadEntity load1 = new LoadEntity();
        load1.setWeight(50.0);
        load1.setDimensions("100x50x50");
        load1.setStatus(LoadStatus.PENDING); // Cambiado a PENDING
        load1.setPallet(palletRepository.findById(1L).orElse(null)); // Asignar a palet 1
        load1.setAssignedTransporter(transportista);
        load1.setDamageReport(null);
        load1.setCreatedAt(LocalDateTime.now());
        load1.setUpdatedAt(LocalDateTime.now());

        LoadEntity load2 = new LoadEntity();
        load2.setWeight(75.0);
        load2.setDimensions("150x75x75");
        load2.setStatus(LoadStatus.IN_TRANSIT); // Cambiado a IN_TRANSIT
        load2.setPallet(palletRepository.findById(2L).orElse(null)); // Asignar a palet 2
        load2.setAssignedTransporter(transportista);
        load2.setDamageReport(null);
        load2.setCreatedAt(LocalDateTime.now());
        load2.setUpdatedAt(LocalDateTime.now());

        loadRepository.saveAll(Arrays.asList(load1, load2));
    }
}
