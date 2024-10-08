package com.riwi.PalletTrack.application.controllers;

import com.riwi.PalletTrack.application.dtos.request.AuditLogRequest;
import com.riwi.PalletTrack.application.dtos.response.AuditLogResponse;
import com.riwi.PalletTrack.domain.ports.services.IAuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final IAuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLogResponse>> getAuditLogs(@RequestParam String startDate, @RequestParam String endDate) {
        AuditLogRequest request = new AuditLogRequest(startDate, endDate);
        List<AuditLogResponse> auditLogs = auditLogService.getAuditLogs(request);
        return new ResponseEntity<>(auditLogs, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<byte[]> generatePdfReport(@RequestBody List<AuditLogResponse> auditLogs) {
        byte[] pdfReport = auditLogService.generatePdfReport(auditLogs);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=audit-report.pdf")
                .body(pdfReport);
    }
}
