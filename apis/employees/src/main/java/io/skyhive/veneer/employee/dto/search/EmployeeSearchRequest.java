package io.skyhive.veneer.employee.dto.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.employee.dto.Skill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class EmployeeSearchRequest {
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
    @Schema(description = "Skills that are needed to be searched for")
    private List<Skill> skills;
    @Schema(description = "Job Titles of the employees to search for")
    private List<String> jobTitles;
    @Schema(description = "Employees Hired from date")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    private LocalDateTime hiredFrom;
    @Schema(description = "Employees Hired till date")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    private LocalDateTime hiredTo;
    @Schema(description = "Degree filter for an employee")
    private String degree;
    @Schema(description = "Country code to look for employees")
    private String country;
    @Schema(description = "Location information to search for the employee")
    private LocationRequest location;
    @Schema(description = "Minimum Match percentage to show results",
            defaultValue = "20")
    @Min(value = 0, message = "minMatchPercentage should be greater than or " +
            "equal to 0")
    @Max(value = 100, message = "minMatchPercentage should be less than or equal " +
            "to 100")
    private float minMatchPercentage = 20;
    @Schema(description = "Company details to look for in an employee")
    private CompanyDetails companyDetails;
    @Schema(description = "Desired Job Titles of the employees to search for")
    private List<String> desiredJobTitles;
    @Schema(description = "Employee Studied University name")
    private String university;
}
