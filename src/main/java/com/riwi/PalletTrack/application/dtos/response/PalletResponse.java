package com.riwi.PalletTrack.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PalletResponse {
    private Long id;
    private String location;
    private double maxWeight;
    private String status;
    private String createdAt;
    private String updatedAt;
}
