package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Relocate.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum Relocate {
    /**
     * None relocate.
     */
    None,
    /**
     * Willing relocate relocate.
     */
    WillingRelocate,
    /**
     * No relocation relocate.
     */
    NoRelocation;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

