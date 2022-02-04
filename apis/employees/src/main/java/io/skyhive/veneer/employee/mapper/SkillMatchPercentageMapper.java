package io.skyhive.veneer.employee.mapper;

import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.employee.dto.search.SkillMatchPercentage;
import org.mapstruct.Mapper;

/**
 * @author krishna
 * @created 29/12/21
 * @project skyhive-veeneer
 */
@Mapper
public interface SkillMatchPercentageMapper {
    SkillMatchPercentage toSkillMatchPercentage(SkillsMatchPercentageResponse response);
}
