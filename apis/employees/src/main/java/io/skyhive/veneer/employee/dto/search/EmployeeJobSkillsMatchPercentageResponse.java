package io.skyhive.veneer.employee.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.skyhive.veneer.employee.dto.Skill;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author krishna
 * @created 29/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeJobSkillsMatchPercentageResponse {
    private SkillMatchPercentage normal;
    private SkillMatchPercentage desired;
}
