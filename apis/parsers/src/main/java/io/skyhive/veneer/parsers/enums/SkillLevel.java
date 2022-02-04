package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Skill level.
 * @author jayaram
 * @created 03/01/2022
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

