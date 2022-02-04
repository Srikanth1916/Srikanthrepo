package io.skyhive.veneer.employee.dto.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class PaginationRequest {

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
    @Schema(description = "Ignores location from Job",
            defaultValue = "false")
    private Boolean ignoreLocation = false;

    @Schema(description = "Radius from the Job location",
            defaultValue = "20")
    @Min(value = 0, message = "radius should be greater than or " +
            "equal to 0")
    private int radius = 20;
    @Schema(description = "Ignores Job Titles from Job",
            defaultValue = "false")
    private Boolean ignoreJobTitle = false;

    @Schema(description = "Minimum Match percentage for the list to display",
            defaultValue = "20")
    @Min(value = 0, message = "minMatchPercentage should be greater than or " +
            "equal to 0")
    @Max(value = 100, message = "minMatchPercentage should be less than or equal " +
            "to 100")
    private float minimumMatchPercentage = 20;

}
