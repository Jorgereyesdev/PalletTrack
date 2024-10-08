package com.riwi.PalletTrack.application.controllers;

import com.riwi.PalletTrack.application.dtos.request.LoadRequest;
import com.riwi.PalletTrack.application.dtos.response.LoadResponse;
import com.riwi.PalletTrack.domain.ports.services.ILoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loads")
@RequiredArgsConstructor
public class LoadController {

    private final ILoadService loadService;

    @GetMapping
    public ResponseEntity<List<LoadResponse>> getAllLoads() {
        List<LoadResponse> loads = loadService.getAllLoads();
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoadResponse> getLoadById(@PathVariable Long id) {
        LoadResponse load = loadService.getLoadById(id);
        return ResponseEntity.ok(load);
    }

    @PostMapping
    public ResponseEntity<LoadResponse> createLoad(@RequestBody LoadRequest request) {
        LoadResponse newLoad = loadService.createLoad(request);
        return ResponseEntity.ok(newLoad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoadResponse> updateLoad(@PathVariable Long id, @RequestBody LoadRequest request) {
        LoadResponse updatedLoad = loadService.updateLoad(id, request);
        return ResponseEntity.ok(updatedLoad);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LoadResponse> updateLoadStatus(@PathVariable Long id, @RequestBody String status) {
        LoadResponse updatedLoad = loadService.updateLoadStatus(id, status);
        return ResponseEntity.ok(updatedLoad);
    }

    @PatchMapping("/{id}/damage")
    public ResponseEntity<LoadResponse> reportLoadDamage(@PathVariable Long id, @RequestBody String damageReport) {
        LoadResponse updatedLoad = loadService.reportLoadDamage(id, damageReport);
        return ResponseEntity.ok(updatedLoad);
    }

    @GetMapping("/pallets/{palletId}")
    public ResponseEntity<List<LoadResponse>> getLoadsByPalletId(@PathVariable Long palletId) {
        List<LoadResponse> loads = loadService.getLoadsByPalletId(palletId);
        return ResponseEntity.ok(loads);
    }
}
