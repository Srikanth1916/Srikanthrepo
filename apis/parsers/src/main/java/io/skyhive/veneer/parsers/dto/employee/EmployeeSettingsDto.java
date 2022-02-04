package io.skyhive.veneer.parsers.dto.employee;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Employee settings dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class EmployeeSettingsDto {
    private boolean allowPeerMentorship;
    private boolean notifySkillUpdateByTraining;
    private boolean notifySkillUpdateByMentorship;
    private boolean notifySkillUpdateDirect;
    private boolean eligibleForInternalJobs;
    private boolean eligibleForExternalJobs;
    private boolean eligibleForAccentureJobs;
}

