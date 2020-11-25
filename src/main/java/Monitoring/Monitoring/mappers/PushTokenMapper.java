package Monitoring.Monitoring.mappers;

import Monitoring.Monitoring.db.models.PushTokens;
import Monitoring.Monitoring.dto.api.viewmodels.request.VmPushTokenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PushTokenMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updateTokenDate", ignore = true)
    PushTokens mapToPushTokens(VmPushTokenRequest source);
}
