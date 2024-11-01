package fatec.bytelabss.api.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CustomQuerySQLConverter implements AttributeConverter<CustomQuerySQL, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(CustomQuerySQL customQuerySQL) {
        try {
            return objectMapper.writeValueAsString(customQuerySQL);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert CustomQuerySQL to JSON string", e);
        }
    }

    @Override
    public CustomQuerySQL convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, CustomQuerySQL.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON string to CustomQuerySQL", e);
        }
    }
}
