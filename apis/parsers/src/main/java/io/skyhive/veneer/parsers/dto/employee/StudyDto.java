package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Study dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Setter
@Getter
public class StudyDto {
    private DictionaryDto institution;

    private DictionaryDto areaOfStudy;

    private DictionaryDto degree;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean isStudyNotCompleted;

    private boolean noDatesProvided;

    private boolean current;

    private boolean mlSkillsSuggested;
}
