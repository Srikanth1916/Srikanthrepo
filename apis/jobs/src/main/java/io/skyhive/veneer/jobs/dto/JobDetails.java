package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * The type Job details.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class JobDetails {
    @Schema(description = "Title of the Job", required = true)
    @NotBlank(message = "Title is required")
    private String title;
    @Schema(description = "Language for the job description ex: for US --> en-US")
    private String language;
    @Schema(description = "It will keep the Job as confidential", defaultValue = "false")
    private boolean isConfidential;
    @NotNull(message = "Type should be any of the value fulltime, parttime, contract, gig, freelance, volunteer, internship, oncall")
    @Schema(description = "Job Work type information")
    private JobType type;
    @NotNull(message = "skill level is required, allowed values entry, intermediate, experienced")
    @Schema(description = "Skill level required for the Job")
    private SkillLevel skillLevel;
    @NotNull(message = "Skills are mandatory")
    @Schema(description = "Skills needed for the Job")
    private List<SkillWithRequirement> skills;
    @Schema(description = "Description of the job")
    private String description;
    @NotBlank(message = "reference code is mandatory")
    @Schema(description = "Job reference code from external sources")
    private String referenceCode;
    @Schema(description = "Job travel information")
    private Travel travel = Travel.None;

}
