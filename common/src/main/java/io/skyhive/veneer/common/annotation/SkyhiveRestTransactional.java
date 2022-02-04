package io.skyhive.veneer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Skyhive rest transactional.
 *
 * @author krishna
 * @created 17 /11/21
 * @project skyhive -veeneer
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkyhiveRestTransactional {
    /**
     * Service object string.
     *
     * @return the string
     */
    String serviceObject() default "";

}
