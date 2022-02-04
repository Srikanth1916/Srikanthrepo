package io.skyhive.veneer.parsers.dto.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Employee compensation dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class EmployeeCompensationDto {
    private double ratePerHour;
    private double annualSalary;
    private boolean noMinimum;
    private CountryDictionaryDto currency;
}
