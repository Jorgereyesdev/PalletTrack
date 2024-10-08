package com.riwi.PalletTrack.application.services;

import com.riwi.PalletTrack.application.dtos.request.AuditLogRequest;
import com.riwi.PalletTrack.application.dtos.response.AuditLogResponse;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.AuditLogEntity;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.repository.AuditLogRepository;
import com.riwi.PalletTrack.domain.ports.services.IAuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogService implements IAuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public List<AuditLogResponse> getAuditLogs(AuditLogRequest request) {
        List<AuditLogEntity> logs = auditLogRepository.findByTimestampBetween(request.getStartDate(), request.getEndDate());
        return logs.stream().map(this::toAuditLogResponse).collect(Collectors.toList());
    }

    @Override
    public void logAction(String action, Long userId, String details) {
        AuditLogEntity log = new AuditLogEntity();
        log.setAction(action);
        log.setUserId(userId);
        log.setTimestamp(LocalDateTime.now());
        log.setDetails(details);
        auditLogRepository.save(log);
    }

    @Override
    public byte[] generatePdfReport(List<AuditLogResponse> auditLogs) {
        // Implementar la lógica para generar el PDF
        return new byte[0]; // Placeholder
    }

    private AuditLogResponse toAuditLogResponse(AuditLogEntity log) {
        return new AuditLogResponse(log.getId(), log.getAction(), log.getUserId(), log.getTimestamp(), log.getDetails());
    }
}
