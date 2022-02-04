package io.skyhive.veneer.models.es.job;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum JobType {
    /**
     * Fulltime work type.
     */
    Fulltime,
    /**
     * Parttime work type.
     */
    Parttime,
    /**
     * Contract work type.
     */
    Contract,
    /**
     * Gig work type.
     */
    Gig,
    /**
     * Freelance work type.
     */
    Freelance,
    /**
     * Volunteer work type.
     */
    Volunteer,
    /**
     * Internship work type.
     */
    Internship,
    /**
     * Oncall work type.
     */
    Oncall;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
