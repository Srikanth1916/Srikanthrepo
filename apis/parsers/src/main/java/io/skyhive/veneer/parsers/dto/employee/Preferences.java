package io.skyhive.veneer.parsers.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Preferences.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Preferences {
    @Schema(description = "Compensation details of an employee")
    private Compensation compensation;
    @Schema(description = "Employee work location remote or not")
    private String remote;
    @Schema(description = "Employee willingness to relocate")
    private String relocate;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isPrivate;

}
