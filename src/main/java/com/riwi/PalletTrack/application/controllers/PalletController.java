package com.riwi.PalletTrack.application.controllers;

import com.riwi.PalletTrack.application.dtos.request.PalletRequest;
import com.riwi.PalletTrack.application.dtos.response.PalletResponse;
import com.riwi.PalletTrack.domain.ports.services.IPalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pallets")
public class PalletController {

    private final IPalletService palletService;

    @Autowired
    public PalletController(IPalletService palletService) {
        this.palletService = palletService;
    }

    @GetMapping
    public ResponseEntity<List<PalletResponse>> getAllPallets() {
        List<PalletResponse> pallets = palletService.getAllPallets();
        return ResponseEntity.ok(pallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalletResponse> getPalletById(@PathVariable Long id) {
        return palletService.getPalletById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PalletResponse> createPallet(@Valid @RequestBody PalletRequest palletRequest) {
        PalletResponse createdPallet = palletService.createPallet(palletRequest);
        return ResponseEntity.status(201).body(createdPallet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PalletResponse> updatePallet(@PathVariable Long id, @Valid @RequestBody PalletRequest palletRequest) {
        return palletService.updatePallet(id, palletRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePallet(@PathVariable Long id) {
        if (palletService.deletePallet(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
