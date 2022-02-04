package io.skyhive.veneer.parsers.dto.employee;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill client data dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SkillClientDataDto {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String dictionaryClientEntityId;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String clientSkillTitle;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private List<String> skillCategoryIds;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private ClientComplexityDto clientComplexity;
}

