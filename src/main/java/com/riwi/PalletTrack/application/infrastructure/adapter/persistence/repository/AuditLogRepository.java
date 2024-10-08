package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {

    List<AuditLogEntity> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
