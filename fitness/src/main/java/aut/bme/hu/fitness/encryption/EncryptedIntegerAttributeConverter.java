package aut.bme.hu.fitness.encryption;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter
public class EncryptedIntegerAttributeConverter implements AttributeConverter<Integer, String> {

    @Override
    public String convertToDatabaseColumn(Integer attribute) {
        return attribute == null ? null : EncryptionUtil.encrypt(attribute.toString());
    }

    @Override
    public Integer convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Integer.valueOf(EncryptionUtil.decrypt(dbData));
    }
}

