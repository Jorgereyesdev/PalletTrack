package com.riwi.PalletTrack.domain.ports.services;

import com.riwi.PalletTrack.application.dtos.request.PalletRequest;
import com.riwi.PalletTrack.application.dtos.response.PalletResponse;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.domain.model.Pallet;

import java.util.List;
import java.util.Optional;

public interface IPalletService {
    List<PalletResponse> getAllPallets();
    Optional<PalletResponse> getPalletById(Long id);
    Optional<PalletEntity> getPalletEntityById(Long id);

    PalletResponse createPallet(PalletRequest palletRequest);
    Optional<PalletResponse> updatePallet(Long id, PalletRequest palletRequest);
    boolean deletePallet(Long id);
}
