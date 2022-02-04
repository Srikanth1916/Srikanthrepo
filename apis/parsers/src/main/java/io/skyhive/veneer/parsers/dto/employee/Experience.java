package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Experience.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Experience {
    @Schema(description = "previous occupation of the employee")
    private Occupation occupation;
    @Schema(description = "previous company where employee has worked")
    private String company;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Start date of previous work of an employee")
    private LocalDateTime startDate;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "End date of previous work of an employee")
    private LocalDateTime endDate;
    @Schema(description = "employee working currenty or not")
    private boolean current;
    @Schema(description = "description of experience")
    private String description;
}

