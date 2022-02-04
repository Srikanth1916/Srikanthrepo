package io.skyhive.veneer.parsers.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.skyhive.veneer.parsers.enums.Duration;
import io.skyhive.veneer.parsers.enums.PayType;
import io.skyhive.veneer.parsers.enums.Relocate;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Compensation.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Compensation {
    @Schema(description = "Pay type(None,Hourly,Project,Salary) an employee")
    private PayType type;
    @Schema(description = "Pay amount of an employee")
    private Double payRate;
    @Schema(description = "currency type of payment for an employee")
    private String currency;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Duration duration;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private int quantity;
    @Schema(description = "number of hours per week spent on work by an employee")
    private int hoursPerWeek;
    @Schema(description = "True/False if any benefits to the respective employee")
    private Boolean benefits;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private Boolean hidden;
    @Schema(description = "Willingness of employee to relocate(Relocate,No Relocation,None")
    private Relocate relocate;
}

