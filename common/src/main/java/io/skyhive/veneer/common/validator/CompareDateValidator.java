package io.skyhive.veneer.common.validator;

import io.skyhive.veneer.common.annotation.CompareDate;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 03/01/22
 * @project skyhive-veeneer
 */
@Slf4j
public class CompareDateValidator implements ConstraintValidator<CompareDate, Object> {

    private String startFieldName;
    private String endFieldName;

    @Override
    public void initialize(final CompareDate constraintAnnotation) {
        startFieldName = constraintAnnotation.before();
        endFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Field beforeDateField = value.getClass().getDeclaredField(startFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField = value.getClass().getDeclaredField(endFieldName);
            afterDateField.setAccessible(true);

            final LocalDateTime startDate = (LocalDateTime) beforeDateField.get(value);
            final LocalDateTime endDate = (LocalDateTime) afterDateField.get(value);
            return (startDate == null && endDate == null) || endDate == null ||
                    (startDate != null && startDate.isBefore(endDate));
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
