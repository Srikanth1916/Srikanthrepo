package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Mentorship state.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
public enum MentorshipState {
    /**
     * None mentorship state.
     */
    None,
    /**
     * Pending mentor mentorship state.
     */
    PendingMentor,
    /**
     * Pending mentee mentorship state.
     */
    PendingMentee,
    /**
     * Rejected by mentor mentorship state.
     */
    RejectedByMentor,
    /**
     * Rejected by mentee mentorship state.
     */
    RejectedByMentee,
    /**
     * Active mentorship state.
     */
    Active,
    /**
     * Ended mentorship state.
     */
    Ended,
    /**
     * Cancelled by hr mentorship state.
     */
    CancelledByHr,
    /**
     * Pending hr mentorship state.
     */
    PendingHr;

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}

