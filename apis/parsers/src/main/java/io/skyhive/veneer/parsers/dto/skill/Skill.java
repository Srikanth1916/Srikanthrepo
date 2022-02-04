package io.skyhive.veneer.parsers.dto.skill;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Skill {
    @Schema(description = "ID of an employee")
    private String id;
    @Schema(description = "Title of the skill")
    private String title;
}
