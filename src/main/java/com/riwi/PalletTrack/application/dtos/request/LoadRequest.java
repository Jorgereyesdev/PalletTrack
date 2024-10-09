package com.riwi.PalletTrack.application.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadRequest {

    private Double weight;

    private String dimensions;

    private String status;

    private Long palletId;
}
