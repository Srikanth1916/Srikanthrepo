package io.skyhive.veneer.common.annotation;

import io.skyhive.veneer.common.feign.FeignHttpExceptionHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Handle feign error.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandleFeignError {
    /**
     * Value class.
     *
     * @return the class
     */
    Class<? extends FeignHttpExceptionHandler> value();
}
