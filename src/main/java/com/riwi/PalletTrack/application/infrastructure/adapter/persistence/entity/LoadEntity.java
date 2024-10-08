package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity;

import com.riwi.PalletTrack.domain.model.LoadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loads")
public class LoadEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double weight;
    private String dimensions;

    @Enumerated(EnumType.STRING)
    private LoadStatus status;

    @ManyToOne
    @JoinColumn(name = "pallet_id")
    private PalletEntity pallet;

    @ManyToOne
    @JoinColumn(name = "transporter_id")
    private UserEntity assignedTransporter;

    private String damageReport;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

