package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Training dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class TrainingDto {
    private DictionaryDto institution;

    private DictionaryDto training;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean current;

    private boolean mlSkillsSuggested;
}
