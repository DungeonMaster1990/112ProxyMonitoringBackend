package ru.vtb.monitoring.vtb112.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vtb.monitoring.vtb112.db.models.PushTokens;
import ru.vtb.monitoring.vtb112.dto.api.viewmodels.request.VmPushTokenRequest;

@Mapper(componentModel = "spring")
public interface PushTokenMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateTokenDate", ignore = true)
    PushTokens mapToPushTokens(VmPushTokenRequest source);
}