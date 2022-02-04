package io.skyhive.veneer.parsers.dto.employee;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Name.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class Name {

    @NotBlank(message = "First Name is required")
    @Schema(description = "First name of an employee")
    private String firstname;
    @NotBlank(message = "last name is required")
    @Schema(description = "Last name of an employee")
    private String lastname;

}

