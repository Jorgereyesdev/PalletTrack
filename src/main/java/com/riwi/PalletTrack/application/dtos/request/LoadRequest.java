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

    @NotNull
    private Double weight;

    @NotBlank
    private String dimensions;

    @NotBlank
    private String status;

    @NotNull
    private Long palletId;
}
