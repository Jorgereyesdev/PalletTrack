package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.domain.model.Pallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletRepository extends JpaRepository<PalletEntity, Long> {
}
