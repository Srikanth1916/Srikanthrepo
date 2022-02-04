package io.skyhive.veneer.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Preferences.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Preferences {
    @Schema(description = "Compensation details of an employee")
    private Compensation compensation;
    @Schema(description = "Employee work location remote or not")
    private WorkType remote;
    @Schema(description = "Employee willingness to relocate")
    private Relocate relocate;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isPrivate;
}
