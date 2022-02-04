package io.skyhive.veneer.employee.dto.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author krishna
 * @created 06/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class CompanyDetails {
    @Schema(description = "Company name to look for in the employee experience")
    @NotNull(message = "Company name cannot be null")
    @NotBlank(message = "Company name cannot be blank")
    private String name;
    @Schema(description = "Searches for employees working in the company name" +
            " at present ")
    private Boolean current;
}
