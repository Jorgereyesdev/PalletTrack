package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper;

import com.riwi.PalletTrack.application.dtos.request.PalletRequest;
import com.riwi.PalletTrack.application.dtos.response.PalletResponse;
import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.PalletEntity;
import com.riwi.PalletTrack.domain.model.Pallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PalletMapper {
    PalletMapper INSTANCE = Mappers.getMapper(PalletMapper.class);

    // Convertir de PalletRequest a Pallet
    Pallet toDomain(PalletRequest palletRequest);

    // Convertir de Pallet a PalletEntity
    PalletEntity toEntity(Pallet pallet);

    // Convertir de PalletEntity a Pallet
    Pallet toDomain(PalletEntity palletEntity);

    // Convertir de Pallet a PalletResponse
    PalletResponse toResponse(Pallet pallet);

    // Opción adicional: Convertir de PalletEntity a PalletResponse si es necesario
    PalletResponse toResponseFromEntity(PalletEntity palletEntity);
}

