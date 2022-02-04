package io.skyhive.veneer.common.annotation;

import io.skyhive.veneer.common.validator.CompareDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author krishna
 * @created 03/01/22
 * @project skyhive-veeneer
 */
@Constraint(validatedBy = CompareDateValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CompareDate {
    String message() default "Invalid dates provided";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String before();
    String after();
}
