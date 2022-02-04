package io.skyhive.veneer.models.es.job;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum PayType {
    /**
     * None pay type.
     */
    None,
    /**
     * Hourly pay type.
     */
    Hourly,
    /**
     * Project pay type.
     */
    Project,
    /**
     * Salary pay type.
     */
    Salary;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
