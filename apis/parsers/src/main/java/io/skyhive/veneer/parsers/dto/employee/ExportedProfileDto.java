package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import io.skyhive.veneer.parsers.enums.CareerPathState;
import io.skyhive.veneer.parsers.enums.LookingForWorkStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Exported profile dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class ExportedProfileDto {
    private LocalDateTime profileWizardFinishedAt;

    private LookingForWorkStatus lookingForWork;

    private CareerPathState careerPathState;

    private String canonicalSeoURL;
}

