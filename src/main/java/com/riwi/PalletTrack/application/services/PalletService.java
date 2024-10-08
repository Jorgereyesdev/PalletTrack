package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.dtos.request.PalletRequest;
import com.riwi.PalletTrack.application.dtos.response.PalletResponse;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper.PalletMapper;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.PalletRepository;
import com.riwi.PalletTrack.domain.model.Pallet;
import com.riwi.PalletTrack.domain.ports.services.IPalletService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PalletService implements IPalletService {

    private final PalletRepository palletRepository;
    private final PalletMapper palletMapper;
    private final Validator validator;

    @Autowired
    public PalletService(PalletRepository palletRepository, PalletMapper palletMapper) {
        this.palletRepository = palletRepository;
        this.palletMapper = palletMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public List<PalletResponse> getAllPallets() {
        List<PalletEntity> palletEntities = palletRepository.findAll();

        return palletEntities.stream()
                .map(palletMapper::toDomain)
                .map(palletMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PalletResponse> getPalletById(Long id) {
        return palletRepository.findById(id)
                .map(palletMapper::toDomain) // Convierte de PalletEntity a Pallet
                .map(palletMapper::toResponse); // Convierte de Pallet a PalletResponse
    }

    @Override
    public Optional<PalletEntity> getPalletEntityById(Long id) {
        return palletRepository.findById(id);
    }

    @Override
    public PalletResponse createPallet(PalletRequest palletRequest) {
        Set<ConstraintViolation<PalletRequest>> violations = validator.validate(palletRequest);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ConstraintViolation<PalletRequest> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Convertir de PalletRequest a Pallet
        Pallet pallet = palletMapper.toDomain(palletRequest);

        // Guardar el Pallet en el repositorio (convierte de Pallet a PalletEntity)
        PalletEntity savedPalletEntity = palletRepository.save(palletMapper.toEntity(pallet));

        // Convertir a Pallet desde PalletEntity
        Pallet savedPallet = palletMapper.toDomain(savedPalletEntity);

        // Convertir a PalletResponse y retornarlo
        return palletMapper.toResponse(savedPallet);
    }

    @Override
    public Optional<PalletResponse> updatePallet(Long id, PalletRequest palletRequest) {
        if (palletRepository.existsById(id)) {
            Set<ConstraintViolation<PalletRequest>> violations = validator.validate(palletRequest);
            if (!violations.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder();
                for (ConstraintViolation<PalletRequest> violation : violations) {
                    errorMessage.append(violation.getMessage()).append("; ");
                }
                throw new IllegalArgumentException(errorMessage.toString());
            }

            // Convierte de PalletRequest a Pallet
            Pallet pallet = palletMapper.toDomain(palletRequest);
            pallet.setId(id); // Asigna el ID del pallet existente

            // Convierte el Pallet a PalletEntity y guarda
            PalletEntity updatedPalletEntity = palletRepository.save(palletMapper.toEntity(pallet));

            // Convierte de PalletEntity a PalletResponse y lo retorna
            return Optional.of(palletMapper.toResponseFromEntity(updatedPalletEntity));
        }
        return Optional.empty();
    }

    @Override
    public boolean deletePallet(Long id) {
        if (palletRepository.existsById(id)) {
            palletRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
