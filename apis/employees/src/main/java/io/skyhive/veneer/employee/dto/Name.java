package io.skyhive.veneer.employee.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Name.
 *
 * @author krishna
 * @created 22 /10/21
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
