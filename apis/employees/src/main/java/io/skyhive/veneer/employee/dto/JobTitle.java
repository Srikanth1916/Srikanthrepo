package io.skyhive.veneer.employee.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Job title.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
