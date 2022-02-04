package io.skyhive.veneer.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Skyhive local date time serializer.
 *
 * @author krishna
 * @created 11 /11/21
 * @project skyhive -veeneer
 */
public class SkyhiveLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private final String datePattren = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern(datePattren));
        jsonGenerator.writeString(formattedDate);
    }
}
