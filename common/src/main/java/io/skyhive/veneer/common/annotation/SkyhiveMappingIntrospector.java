package io.skyhive.veneer.common.annotation;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import io.skyhive.veneer.common.serializer.SkyhiveMappedFieldDeserializer;
import io.skyhive.veneer.common.serializer.SkyhiveMappedFieldSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Skyhive mapping introspector.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Component
public class SkyhiveMappingIntrospector extends NopAnnotationIntrospector {
    /**
     * The Skyhive mapped field serializer.
     */
    @Autowired
    SkyhiveMappedFieldSerializer skyhiveMappedFieldSerializer;

    /**
     * The Skyhive mapped field deserializer.
     */
    @Autowired
    SkyhiveMappedFieldDeserializer skyhiveMappedFieldDeserializer;

    @Override
    public Object findSerializer(Annotated am) {
        SkyhiveMappedField annotation = am.getAnnotation(SkyhiveMappedField.class);
        if (annotation != null) {
            return skyhiveMappedFieldSerializer;
        }
        return null;
    }

    @Override
    public Object findDeserializer(Annotated am) {
        SkyhiveMappedField annotation = am.getAnnotation(SkyhiveMappedField.class);
        if (annotation != null) {
            return skyhiveMappedFieldDeserializer;
        }
        return null;
    }
}
