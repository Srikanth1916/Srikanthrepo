package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sovren.models.resume.education.Degree;

import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Study.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @Schema(description = "dates of study provided by the employee or not")
    private boolean noDatesProvided;
    @Schema(description = "persuing studies currently or not")
    private boolean current;
}

