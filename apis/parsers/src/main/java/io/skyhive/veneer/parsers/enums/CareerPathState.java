package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Career path state.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
public enum CareerPathState {
    /**
     * None career path state.
     */
    None,
    /**
     * Started career path state.
     */
    Started,
    /**
     * Completed career path state.
     */
    Completed,
    /**
     * Closed career path state.
     */
    Closed,
    /**
     * Requested career path state.
     */
    Requested;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
