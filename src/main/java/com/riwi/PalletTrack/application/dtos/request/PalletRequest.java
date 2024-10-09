package com.riwi.PalletTrack.application.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Valid
public class PalletRequest {
    private String location;

    private double maxWeight;

    private String status;
}
