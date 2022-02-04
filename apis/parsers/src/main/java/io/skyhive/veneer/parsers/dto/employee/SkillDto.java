package io.skyhive.veneer.parsers.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.skyhive.veneer.parsers.enums.SkillLevel;
import io.skyhive.veneer.parsers.enums.SkillType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillDto extends SkillToolDictionaryDto {
    @Schema(description = "Minimum skill level ")
    private SkillLevel level;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillLevel hRAssessedLevel;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillLevel averageScore;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillLevel endorsedSkillLevel;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillAcquisitionInfoDto[] skillAcquisitionInfo;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillType skillType;
}
