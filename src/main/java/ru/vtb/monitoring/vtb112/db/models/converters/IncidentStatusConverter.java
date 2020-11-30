package ru.vtb.monitoring.vtb112.db.models.converters;

import ru.vtb.monitoring.vtb112.db.models.enums.Status;

import javax.persistence.AttributeConverter;

public class IncidentStatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getText();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return Status.getStatusByString(dbData);
    }

}
