package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Skill acquisition info.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SkillAcquisitionInfo {
    @Schema(description = "source of the skill that is aquired by employee")
    private String source;
    @Schema(description = "sourceId of the skill that is aquired by employee")
    private String sourceId;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "date on which skill aquired by the employee")
    private LocalDateTime date;
    @Schema(description = "level of skill aquired by employee")
    private String level;
    @Schema(description = "previous level of skill aquired by employee")
    private String previousLevel;

}
