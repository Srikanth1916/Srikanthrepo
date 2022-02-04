package io.skyhive.veneer.employee.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.annotation.CompareDate;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * The type Experience.
 */
@Getter
@Setter
@CompareDate(before = "startDate", after="endDate", message = "startDate must" +
        " be lower than the endDate")
public class Experience {
    @Schema(description = "previous occupation of the employee")
    private Occupation occupation;
    @Schema(description = "previous company where employee has worked")
    private String company;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "Start date of previous work of an employee")
    @PastOrPresent(message = "StartDate cannot be future date")
    private LocalDateTime startDate;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @Schema(description = "End date of previous work of an employee")
    private LocalDateTime endDate;
    @Schema(description = "employee working currently or not")
    private boolean current;
    @Schema(description = "description of experience")
    private String description;
}
