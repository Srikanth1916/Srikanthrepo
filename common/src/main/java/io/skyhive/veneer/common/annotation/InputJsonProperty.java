package io.skyhive.veneer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Input json property.
 *
 * @author krishna
 * @created 10 /11/21
 * @project skyhive -veeneer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputJsonProperty {
    /**
     * Value string.
     *
     * @return the string
     */
    String value();
}
