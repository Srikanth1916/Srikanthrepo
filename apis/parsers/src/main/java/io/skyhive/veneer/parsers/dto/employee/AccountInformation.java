package io.skyhive.veneer.parsers.dto.employee;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Account information.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */

@Getter
@Setter
public class AccountInformation {
    @NotNull
    @Schema(description = "email specifies email address of an employee")
    private String email;
    @Schema(description = "Type specifies role of an employee  ex: employee")
    private String type;
}

