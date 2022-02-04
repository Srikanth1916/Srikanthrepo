package io.skyhive.veneer.parsers.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enum Employee type.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
public enum EmployeeType {
    /**
     * Employee employee type.
     */
    Employee("employee"),
    /**
     * Ocm employee employee type.
     */
    OCMEmployee("ocmEmployee");
    private final String type;

    EmployeeType(String type) {
        this.type = type;
    }

    /**
     * Gets employee type.
     *
     * @return the employee type
     */
    @Override
    @JsonValue
    public String toString() {
        return type;
    }


}

