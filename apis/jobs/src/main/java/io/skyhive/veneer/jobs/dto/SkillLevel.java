package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
public enum SkillLevel {
    /**
     * Entry skill level.
     */
    Entry,
    /**
     * Intermediate skill level.
     */
    Intermediate,
    /**
     * Experienced skill level.
     */
    Experienced;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }

}
