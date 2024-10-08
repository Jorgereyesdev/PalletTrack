package com.riwi.PalletTrack.application.dtos.response;

import com.riwi.PalletTrack.domain.model.LoadStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadResponse {

    private Long id;

    private Double weight;

    private String dimensions;

    private LoadStatus status;

    private Long palletId;

    private Long transporterId;

    private String damageReport;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
