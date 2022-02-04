package io.skyhive.veneer.parsers.dto.employee;


import io.skyhive.veneer.parsers.enums.Relocate;
import io.skyhive.veneer.parsers.enums.Remote;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Employee job preferences dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class EmployeeJobPreferencesDto {
    private EmployeeCompensationDto compensation;
    private Remote remote;
    private Relocate relocate;
    private boolean isPrivate;
}

