package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
public enum JobStartDate {
    Asap,
    SpecificDate,
    Range;
    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

