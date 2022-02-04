package io.skyhive.veneer.parsers.dto.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Resume extraction response.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class ResumeExtractionResponse {
    private EmployeeDtoResumeParsing extractedProfile;
    private SkillDto[] resumeSkills;
    private DictionaryDto[] recommendedSkills;
}
