package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SkillWithRequirement extends Dictionary {
    @Schema(description = "Skills requirement for the Job")
    private SkillRequirement requirement;
}

