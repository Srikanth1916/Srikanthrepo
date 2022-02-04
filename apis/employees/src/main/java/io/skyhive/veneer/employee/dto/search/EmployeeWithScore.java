package io.skyhive.veneer.employee.dto.search;

import io.skyhive.veneer.employee.dto.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class EmployeeWithScore extends Employee {
    private double quality;
}
