package io.skyhive.veneer.employee.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.annotation.CompareDate;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The type Study.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompareDate(before = "startDate", after="endDate", message = "startDate must" +
        " be lower than the endDate")
public class Study {
    @Schema(description = "Institute in which employee has studies")
    private Institution institution;
    @Schema(description = "Area of study that employee has studies")
    private AreaOfStudy areaOfStudy;
    @Schema(description = "degree details of an employee")
    private Degree degree;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Date of course started by the employee")
    private LocalDateTime startDate;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Date of course ended by the employee")
    private LocalDateTime endDate;
    @Schema(description = "study completed or not by respective employee")
    private boolean isStudyNotCompleted;
    @Schema(description = "persuing studies currently or not")
    private boolean current;
}
