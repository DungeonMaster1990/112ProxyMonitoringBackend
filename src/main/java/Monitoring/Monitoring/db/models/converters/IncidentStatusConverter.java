package Monitoring.Monitoring.db.models.converters;

import Monitoring.Monitoring.db.models.enums.Status;

import javax.persistence.AttributeConverter;

public class IncidentStatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getStatus();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return Status.getStatusByString(dbData);
    }

}
