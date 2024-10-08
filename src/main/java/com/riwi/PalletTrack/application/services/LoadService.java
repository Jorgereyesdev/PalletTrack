package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.dtos.request.LoadRequest;
import com.riwi.PalletTrack.application.dtos.response.LoadResponse;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.LoadEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper.PalletMapper;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.LoadRepository;
import com.riwi.PalletTrack.domain.model.LoadStatus;
import com.riwi.PalletTrack.domain.model.Pallet;
import com.riwi.PalletTrack.domain.ports.services.IAuditLogService;
import com.riwi.PalletTrack.domain.ports.services.ILoadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LoadService implements ILoadService {

    private final LoadRepository loadRepository;
    private final PalletService palletService;
    private final IAuditLogService auditLogService;
    private final PalletMapper palletMapper;

    @Override
    public List<LoadResponse> getAllLoads() {
        return loadRepository.findAll()
                .stream()
                .map(this::toLoadResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoadResponse getLoadById(Long id) {
        LoadEntity load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));
        return toLoadResponse(load);
    }

    @Override
    public LoadResponse createLoad(LoadRequest request) {
        Optional<PalletEntity> optionalPallet = palletService.getPalletEntityById(request.getPalletId());
        PalletEntity pallet = optionalPallet.orElseThrow(() -> new RuntimeException("Pallet not found"));

        LoadEntity load = new LoadEntity();
        load.setWeight(request.getWeight());
        load.setDimensions(request.getDimensions());
        load.setStatus(LoadStatus.valueOf(request.getStatus()));
        load.setPallet(pallet);
        load.setCreatedAt(LocalDateTime.now());
        load.setUpdatedAt(LocalDateTime.now());

        loadRepository.save(load);
        auditLogService.logAction("Create Load", getCurrentUserId(), "Created load with ID: " + load.getId());

        return toLoadResponse(load);
    }

    @Override
    public LoadResponse updateLoad(Long id, LoadRequest request) {
        LoadEntity load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));

        load.setWeight(request.getWeight());
        load.setDimensions(request.getDimensions());
        load.setStatus(LoadStatus.valueOf(request.getStatus()));
        load.setUpdatedAt(LocalDateTime.now());

        loadRepository.save(load);
        auditLogService.logAction("Update Load", getCurrentUserId(), "Updated load with ID: " + load.getId());

        return toLoadResponse(load);
    }

    @Override
    public LoadResponse updateLoadStatus(Long id, String status) {
        LoadEntity load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));

        load.setStatus(LoadStatus.valueOf(status));
        load.setUpdatedAt(LocalDateTime.now());

        loadRepository.save(load);
        auditLogService.logAction("Update Load Status", getCurrentUserId(), "Updated status of load with ID: " + load.getId() + " to " + status);

        return toLoadResponse(load);
    }

    @Override
    public LoadResponse reportLoadDamage(Long id, String damageReport) {
        LoadEntity load = loadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Load not found"));

        load.setDamageReport(damageReport);
        load.setUpdatedAt(LocalDateTime.now());

        loadRepository.save(load);
        auditLogService.logAction("Report Load Damage", getCurrentUserId(), "Reported damage on load with ID: " + load.getId());

        return toLoadResponse(load);
    }

    @Override
    public List<LoadResponse> getLoadsByPalletId(Long palletId) {
        return loadRepository.findByPalletId(palletId)
                .stream()
                .map(this::toLoadResponse)
                .collect(Collectors.toList());
    }

    private Long getCurrentUserId() {
        return 1L; // Placeholder
    }

    private LoadResponse toLoadResponse(LoadEntity load) {
        return new LoadResponse(
                load.getId(),
                load.getWeight(),
                load.getDimensions(),
                load.getStatus(),
                load.getPallet().getId(),
                load.getAssignedTransporter() != null ? load.getAssignedTransporter().getId() : null,
                load.getDamageReport(),
                load.getCreatedAt(),
                load.getUpdatedAt()
        );
    }
}
