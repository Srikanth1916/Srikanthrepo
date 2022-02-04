package io.skyhive.veneer.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author krishna
 * @created 18/01/22
 * @project skyhive-veeneer
 */
public class DoubleToPercentageSerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double quality, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if(quality == null){
            jsonGenerator.writeNull();
        } else {
            double truncatedDouble = BigDecimal.valueOf(quality)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            jsonGenerator.writeNumber(truncatedDouble);
        }
    }
}
