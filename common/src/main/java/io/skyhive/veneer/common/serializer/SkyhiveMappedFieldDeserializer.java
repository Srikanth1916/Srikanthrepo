package io.skyhive.veneer.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.skyhive.veneer.common.annotation.SkyhiveMappedField;
import io.skyhive.veneer.common.entities.VeneerMapping;
import io.skyhive.veneer.common.rest.cachable.CachableMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * The type Skyhive mapped field deserializer.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Component
public class SkyhiveMappedFieldDeserializer extends StdDeserializer<String> implements ContextualDeserializer {
    private CachableMappingRepository cachableMappingRepository;
    private String typeValue;

    /**
     * Instantiates a new Skyhive mapped field deserializer.
     */
    public SkyhiveMappedFieldDeserializer() {
        super(String.class);
    }

    /**
     * Instantiates a new Skyhive mapped field deserializer.
     *
     * @param cachableMappingRepository the cachable mapping repository
     */
    @Autowired
    public SkyhiveMappedFieldDeserializer(@Lazy CachableMappingRepository cachableMappingRepository) {
        this();
        this.cachableMappingRepository = cachableMappingRepository;
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String externalId = jsonParser.getValueAsString();
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        JsonStreamContext parent = parsingContext.getParent();
        Object currentValue = parent.getCurrentValue();
        VeneerMapping mapping = cachableMappingRepository.findByTypeAndExternalId(typeValue == null ? currentValue.getClass().getName() : typeValue, externalId);
        if (mapping != null) {
            return mapping.getSkyhiveId();
        }
        return externalId;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        String propertyValue = null;
        if (beanProperty != null) {
            SkyhiveMappedField annotation = beanProperty.getAnnotation(SkyhiveMappedField.class);
            propertyValue = annotation == null ? null : annotation.type();
        }
        this.typeValue = propertyValue;
        return this;
    }

}
