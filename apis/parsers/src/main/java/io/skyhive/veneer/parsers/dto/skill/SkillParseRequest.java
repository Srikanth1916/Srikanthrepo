package io.skyhive.veneer.parsers.dto.skill;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SkillParseRequest {
    @Schema(description = "Text to extract skills")
    @NotNull(message = "Text cannot be null")
    @NotBlank(message = "Text cannot be blank")
    private String text;
}
