package io.skyhive.veneer.models.es;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum Relocate {
    /**
     * None relocate.
     */
    Maybe,
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
