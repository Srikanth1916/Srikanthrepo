package io.skyhive.veneer.parsers.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Duration.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
public enum Duration {
    /**
     * Hours duration.
     */
    Hours,
    /**
     * Days duration.
     */
    Days,
    /**
     * Weeks duration.
     */
    Weeks,
    /**
     * The Months.
     */
    Months {
        public int getDays(int quantity) {
            return quantity == 0 ? 30 : quantity * 30;
        }

        public Duration getSkyhiveDuration() {
            return Days;
        }
    },
    /**
     * The Years.
     */
    Years {
        public int getDays(int quantity) {
            return quantity == 0 ? 365 : quantity * 365;
        }

        public Duration getSkyhiveDuration() {
            return Days;
        }
    };

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }

    /**
     * Get days int.
     *
     * @param quantity the quantity
     * @return the int
     */
    public int getDays(int quantity) {
        return quantity;
    }

    /**
     * Get skyhive duration duration.
     *
     * @return the duration
     */
    public Duration getSkyhiveDuration() {
        return this;
    }
}

