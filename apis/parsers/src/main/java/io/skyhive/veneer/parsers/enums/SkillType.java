package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Skill type.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum SkillType {
    /**
     * Current job skill type.
     */
    CurrentJob,
    /**
     * Department skill type.
     */
    Department,
    /**
     * Future job skill type.
     */
    FutureJob,
    /**
     * Endorsed skill skill type.
     */
    EndorsedSkill;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

