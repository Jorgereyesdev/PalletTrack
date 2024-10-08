package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.LoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRepository extends JpaRepository<LoadEntity, Long> {
    List<LoadEntity> findByPalletId(Long palletId);
    List<LoadEntity> findByAssignedTransporterId(Long transporterId);
    List<LoadEntity> findByStatus(String status);
}
