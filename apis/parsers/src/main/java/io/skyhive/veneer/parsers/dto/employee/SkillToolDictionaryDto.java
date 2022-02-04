package io.skyhive.veneer.parsers.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill tool dictionary dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter

public class SkillToolDictionaryDto extends DictionaryDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private boolean isTool;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private boolean isLanguage;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private SkillClientDataDto[] clientSkillData;
}
