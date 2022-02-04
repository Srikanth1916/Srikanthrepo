package io.skyhive.veneer.jobs.dto;

import io.skyhive.veneer.common.validator.SHLimitedMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The type Jobs.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Setter
@Getter
public class Job {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @NotNull
    @Valid
    @Schema(description = "The field Country, specifies Job location")
    private Country country;
    @Schema(description = "JobDetails specifies the details of Job")
    @NotNull
    @Valid
    private JobDetails details;
    @Schema(description = "LocationTime specifies the date of joining and other details of Job")
    @NotNull
    @Valid
    private LocationTime locationTime;
    @Schema(description = "Compensation specifies the compensation details of Job,like pay rate,currency etc")
    @NotNull
    @Valid
    private Compensation compensation;
    @Schema(description = "Duties specifies,required duties for respective Job")
    private String duties;
    @Schema(description = "Qualification specifies the required qualification for the Job")
    @Size(max = 50000, message = "qualification length should not exceed 50000 chars")
    private String qualification;
    @Schema(description = "Job Status information and date when there is status change")
    private JobStatus status = new JobStatus();
    @Schema(description = "Metadata information which cannot be indexed")
    private SHLimitedMap<String, String> metadata;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String enterpriseId;

}
