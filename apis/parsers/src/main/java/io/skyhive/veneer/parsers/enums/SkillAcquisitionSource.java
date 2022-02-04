package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Skill acquisition source.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
public enum SkillAcquisitionSource {
    /**
     * None skill acquisition source.
     */
    None,
    /**
     * Self set skill acquisition source.
     */
    SelfSet,
    /**
     * Hr set skill acquisition source.
     */
    HrSet,
    /**
     * Mentorship skill acquisition source.
     */
    Mentorship,
    /**
     * Training skill acquisition source.
     */
    Training,
    /**
     * Resume skill acquisition source.
     */
    Resume;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
