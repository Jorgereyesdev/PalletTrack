package com.riwi.PalletTrack.application.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
@Valid
public class PalletRequest {
    @NotNull(message = "La ubicación no puede ser nula.")
    private String location;

    @Positive(message = "El peso máximo debe ser positivo.")
    private double maxWeight;

    private String status;
}
