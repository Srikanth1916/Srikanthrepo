package io.skyhive.veneer.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.skyhive.veneer.common.annotation.SkyhiveMappedField;
import io.skyhive.veneer.common.entities.VeneerMapping;
import io.skyhive.veneer.common.rest.cachable.CachableMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Skyhive mapped field serializer.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Component
public class SkyhiveMappedFieldSerializer extends StdSerializer<String> implements ContextualSerializer {
    private CachableMappingRepository cachableMappingRepository;
    private String typeValue;

    /**
     * Instantiates a new Skyhive mapped field serializer.
     */
    public SkyhiveMappedFieldSerializer() {
        super(String.class);
    }

    /**
     * Instantiates a new Skyhive mapped field serializer.
     *
     * @param cachableMappingRepository the cachable mapping repository
     */
    @Autowired
    public SkyhiveMappedFieldSerializer(@Lazy CachableMappingRepository cachableMappingRepository) {
        this();
        this.cachableMappingRepository = cachableMappingRepository;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        VeneerMapping skyhiveMapper = cachableMappingRepository.findById(s);
        if (skyhiveMapper != null) {
            jsonGenerator.writeString(skyhiveMapper.getExternalId());
        } else {
            jsonGenerator.writeString(s);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        String propertyValue = null;
        if (beanProperty != null) {
            SkyhiveMappedField annotation = beanProperty.getContextAnnotation(SkyhiveMappedField.class);
            propertyValue = annotation == null ? null : annotation.type();
        }
        this.typeValue = propertyValue;
        return this;
    }
}
