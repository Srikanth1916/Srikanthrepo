package io.skyhive.veneer.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill {

    @Schema(description = "Level of the skill required for the Job")
    private SkillLevel level;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isTool;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private boolean isLanguage;
    @Schema(description = "ID of the Skill")
    @EqualsAndHashCode.Include
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
