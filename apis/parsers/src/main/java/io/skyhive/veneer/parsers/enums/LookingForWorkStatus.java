package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Looking for work status.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum LookingForWorkStatus {
    /**
     * Not looking looking for work status.
     */
    NotLooking,
    /**
     * Looking looking for work status.
     */
    Looking;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

