package com.riwi.PalletTrack.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Load {
    private Long id;
    private Double weight;
    private String dimensions;
    private String status;
    private Long palletId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
