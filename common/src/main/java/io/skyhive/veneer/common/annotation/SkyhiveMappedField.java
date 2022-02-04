package io.skyhive.veneer.common.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Skyhive mapped field.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SkyhiveMappedField {
    /**
     * Type string.
     *
     * @return the string
     */
    String type();
}