package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Enterprise subscription.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum EnterpriseSubscription {
    /**
     * Basic enterprise subscription.
     */
    Basic,
    /**
     * Standard enterprise subscription.
     */
    Standard;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
