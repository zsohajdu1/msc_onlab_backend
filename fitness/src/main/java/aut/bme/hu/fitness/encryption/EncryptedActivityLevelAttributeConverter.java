package aut.bme.hu.fitness.encryption;

import aut.bme.hu.fitness.entity.ActivityLevel;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter
public class EncryptedActivityLevelAttributeConverter implements AttributeConverter<ActivityLevel, String> {

    @Override
    public String convertToDatabaseColumn(ActivityLevel attribute) {
        return attribute == null ? null : EncryptionUtil.encrypt(attribute.name());
    }

    @Override
    public ActivityLevel convertToEntityAttribute(String dbData) {
        return dbData == null ? null : ActivityLevel.valueOf(EncryptionUtil.decrypt(dbData));
    }
}

