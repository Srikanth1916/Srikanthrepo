package io.skyhive.veneer.common.annotation;

import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.stereotype.Component;

/**
 * The type Input json property introspector.
 *
 * @author krishna
 * @created 10 /11/21
 * @project skyhive -veeneer
 */
@Component
public class InputJsonPropertyIntrospector extends JacksonAnnotationIntrospector {
    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
        InputJsonProperty fieldName = a.getAnnotation(InputJsonProperty.class);
        if (fieldName != null) {
            return PropertyName.construct(fieldName.value());
        }
        return super.findNameForDeserialization(a);
    }
}
