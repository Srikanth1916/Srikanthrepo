package io.skyhive.veneer.parsers.dto.employee;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.skyhive.veneer.parsers.enums.SkillLevel;
import io.skyhive.veneer.parsers.enums.SkillType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Skill {

    @Schema(description = "Level of the skill required for the Job")
    private SkillLevel level;
    @Schema(description = "level of assessment by the HR")
    private Object hrAssessedLevel;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String averageScore = "intermediate";
    @Schema(description = "level of the endorsed skill of an employee")
    private Object endorsedSkillLevel;
    @Schema(description = "skill Acquisition Information of an employee")
    private List<SkillAcquisitionInfo> skillAcquisitionInfo;
    @Schema(description = "Type of the skill(CurrentJob, Department,FutureJob, EndorsedSkill)")
    private SkillType skillType;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isTool;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isLanguage;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private List<SkillClientDataDto> clientSkillData;
    @Schema(description = "ID of an employee")
    private String id;
    @Schema(description = "Title of the skill")
    private String title;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private int sortOrder;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String definition;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean clearLevelOnly;

}

