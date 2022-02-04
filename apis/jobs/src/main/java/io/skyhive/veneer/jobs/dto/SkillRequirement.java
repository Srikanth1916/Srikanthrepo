package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
public enum SkillRequirement {
    /**
     * Required skill requirement.
     */
    Required,
    /**
     * Optional skill requirement.
     */
    Optional;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

