package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Remote.
 */
public enum Remote {
    /**
     * None remote.
     */
    None,
    /**
     * Willing remote remote.
     */
    WillingRemote,
    /**
     * No remote remote.
     */
    NoRemote,
    /**
     * Whatever remote.
     */
    Whatever;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

