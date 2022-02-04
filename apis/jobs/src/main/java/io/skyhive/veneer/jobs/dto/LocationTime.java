package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.annotation.CompareDate;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * The type Location time.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompareDate(before = "startDate", after="endDate", message =
        "startDate must be lower than the endDate")
public class LocationTime {
    @Schema(description = "Location information for the Job")
    private Location location;
    @Schema(description = "Specifies remote location for Job", defaultValue =
            "onsite")
    private WorkType workType = WorkType.Onsite;
    @Schema(description = "Specifies Job start date Allowed formats: " +
            "[yyyy-MM-dd'T'HH:mm:ss'Z',yyyy-MM-dd'T'HH:mm:ss.SSS'Z',yyyy-MM-dd]")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime startDate;
    @Schema(description = "Specifies Job End date Allowed formats: " +
            "[yyyy-MM-dd'T'HH:mm:ss'Z',yyyy-MM-dd'T'HH:mm:ss.SSS'Z',yyyy-MM-dd]")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime endDate;
    @Schema(description = "Specifies start hour of the Job", defaultValue = "0")
    private int startHour;
    @Schema(description = "Specifies Job duration")
    private Duration duration;
    @Schema(description = "Specifies number of positions required for this job",
            defaultValue = "1")
    @Min(value = 1, message = "quantity value should be greater than " +
            "or equal to 1")
    private int quantity = 1;
}
