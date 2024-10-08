package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper;

import com.riwi.PalletTrack.application.dtos.request.LoadRequest;
import com.riwi.PalletTrack.application.dtos.response.LoadResponse;
import com.riwi.PalletTrack.domain.model.Load;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoadMapper {
    LoadMapper INSTANCE = Mappers.getMapper(LoadMapper.class);

    Load toEntity(LoadRequest loadRequest);

    LoadResponse toResponse(Load load);
}
