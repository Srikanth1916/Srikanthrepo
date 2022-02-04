package io.skyhive.veneer.common.serializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The type Multi local date time de serializer.
 *
 * @author krishna
 * @created 11 /11/21
 * @project skyhive -veeneer
 */
public class MultiLocalDateTimeDeSerializer extends StdDeserializer<LocalDateTime> {
    private static final String[] DATE_TIME_FORMATS = new String[]{
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss"
    };
    private static final String[] DATE_FORMATS = new String[]{"yyyy-MM-dd"};

    /**
     * Instantiates a new Multi local date time de serializer.
     */
    public MultiLocalDateTimeDeSerializer() {
        super((JavaType) null);
    }

    /**
     * Instantiates a new Multi local date time de serializer.
     *
     * @param vc the vc
     */
    public MultiLocalDateTimeDeSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final String date = node.textValue();

        for (String DATE_TIME_FORMAT : DATE_TIME_FORMATS) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            } catch (DateTimeParseException e) {
            }
        }
        for (String DATE_FORMAT : DATE_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
                return LocalDateTime.of(localDate, LocalDateTime.MIN.toLocalTime());
            } catch (DateTimeParseException e) {
            }
        }
        throw new JsonParseException(jsonParser, "Unparseable date: \"" + date + "\". Supported formats: " + Arrays.toString(DATE_TIME_FORMATS));
    }
}
