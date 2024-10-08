package com.riwi.PalletTrack.application.infrastructure.adapter.persistence.mapper;

import com.riwi.PalletTrack.application.infrastructure.adapter.persistence.entity.UserEntity;
import com.riwi.PalletTrack.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDomain(UserEntity entity);

    UserEntity toEntity(User user);
}
