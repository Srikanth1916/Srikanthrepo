package io.skyhive.veneer.jobs.dto.search;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.jobs.dto.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobSearchRequest {
    @Schema(description = "Specifies the number of results to be returned",
            defaultValue = "10")
    @Min(value = 1, message = "resultsPerPage should be greater than or " +
            "equal to 1")
    @Max(value = 50, message = "resultsPerPage should be less than or equal " +
            "to 50")
    private int resultsPerPage = 10;
    @Schema(description = "Specifies the page number",
            defaultValue = "0")
    @Min(value = 0, message = "page should be greater than or equal to 0")
    private int page=0;
    @Schema(description = "Specifies the Job Type")
    private JobType type;
    @Schema(description = "Skills that are needed to be searched for")
    private Set<SkillWithRequirement> skills;
    @Schema(description = "Country code  that is needed to be searched for")
    private String country;
    @Schema(description = "Job Titles to search for the Jobs")
    private List<String> jobTitles;
    @Schema(description = "Location information to search for the Jobs")
    private LocationRequest location;
    @Schema(description = "Minimum Match percentage to show results",
            defaultValue = "20")
    @Min(value = 0, message = "minMatchPercentage should be greater than or " +
            "equal to 0")
    @Max(value = 100, message = "minMatchPercentage should be less than or equal " +
            "to 100")
    private float minMatchPercentage = 20;
    @Schema(description = "WorkType to be filtered upon")
    private WorkType workType;
    @Schema(description = "Jobs that are posted on or after fromPosted date")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    private LocalDateTime fromPostedDate;
    @Schema(description = "Jobs that are posted on or before toPosted date")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    private LocalDateTime toPostedDate;
    @Schema(description = "Filter by pay type of the Job")
    private PayType payType;
    @Schema(description = "Minimum pay rate value")
    private Double minPayRate;

    @Schema(description = "Results to be sorted based on this field value")
    private JobSearchSortField sortBy = JobSearchSortField.quality;

    @Schema(description = "Travel information of the Job")
    private Travel travel;

    @Schema(description = "Job Status information of Job")
    private JobState state;
}
