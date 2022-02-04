package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import io.skyhive.veneer.parsers.enums.SkillAcquisitionSource;
import io.skyhive.veneer.parsers.enums.SkillLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill acquisition info dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class SkillAcquisitionInfoDto {
    private SkillAcquisitionSource source;

    private String sourceId;

    private LocalDateTime date;

    private SkillLevel level;

    private SkillLevel previousLevel;
}

