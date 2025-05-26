package aut.bme.hu.fitness.encryption;

import aut.bme.hu.fitness.entity.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter
public class EncryptedGenderAttributeConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute == null ? null : EncryptionUtil.encrypt(attribute.name());
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Gender.valueOf(EncryptionUtil.decrypt(dbData));
    }
}