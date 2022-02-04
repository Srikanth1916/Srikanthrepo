package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Export state.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum ExportState {
    /**
     * Exported export state.
     */
    Exported,
    /**
     * Registered export state.
     */
    Registered,
    /**
     * Activated export state.
     */
    Activated,
    /**
     * Deactivated export state.
     */
    Deactivated;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
