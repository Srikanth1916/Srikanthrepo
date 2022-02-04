package io.skyhive.veneer.parsers.dto.employee;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Job title.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class JobTitle {
    @Schema(accessMode = AccessMode.READ_ONLY)
    @JsonAlias(value = "titleId")
    private String id;
    @NotBlank(message = "Title is required")
    @Schema(description = "Job Title")
    private String title;
    @Schema(description = "Job Description")
    private String definition;
}
