package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
public enum ExternalPlatform {
    /**
     * None external platform.
     */
    None,
    /**
     * Indeed external platform.
     */
    Indeed,
    /**
     * Taleo external platform.
     */
    Taleo,
    /**
     * Enteprise external platform.
     */
    Enteprise,
    /**
     * Manual external platform.
     */
    Manual,
    /**
     * Ocm external platform.
     */
    OCM,
    /**
     * Obeikan external platform.
     */
    Obeikan;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }

}
