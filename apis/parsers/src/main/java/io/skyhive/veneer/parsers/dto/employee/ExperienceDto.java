package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Experience dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class ExperienceDto {
    private JobTitleTypeaheadDto occupation;

    private String company;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean current;

    private String description;
}

