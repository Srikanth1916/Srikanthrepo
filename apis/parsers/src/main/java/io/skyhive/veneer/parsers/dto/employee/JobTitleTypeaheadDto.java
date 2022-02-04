package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Job title typeahead dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobTitleTypeaheadDto {
    private String id;

    private String titleValue;

    private String title;

    private String[] skillIds;

    private boolean isPosition;

    private boolean isEnterpriseJob;

    private LocalDateTime publishedDate;

    private boolean isSkillBlueprint;
}

