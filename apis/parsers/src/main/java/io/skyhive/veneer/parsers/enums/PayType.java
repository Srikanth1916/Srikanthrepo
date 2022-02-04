package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Pay type.
 *
 * @author jayaram
 * @created 03/01/2022
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

