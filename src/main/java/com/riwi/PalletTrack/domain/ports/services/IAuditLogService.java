package com.riwi.PalletTrack.domain.ports.services;

import com.riwi.PalletTrack.application.dtos.request.AuditLogRequest;
import com.riwi.PalletTrack.application.dtos.response.AuditLogResponse;

import java.util.List;

public interface IAuditLogService {
    List<AuditLogResponse> getAuditLogs(AuditLogRequest request);
    void logAction(String action, Long userId, String details);
    byte[] generatePdfReport(List<AuditLogResponse> auditLogs);
}
