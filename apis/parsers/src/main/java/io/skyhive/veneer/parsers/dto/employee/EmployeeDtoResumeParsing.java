package io.skyhive.veneer.parsers.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.skyhive.veneer.common.entities.location.LocationDto;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a temporary class only for resume parsing should not be used anywhere
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class EmployeeDtoResumeParsing extends EmployeeDto {
    @JsonProperty("location")
    private LocationDto locationObject;
    private Contact contact;
}

