package io.skyhive.veneer.models.mongo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class MatchingSkill extends Dictionary {
    private int complexity = 4;
    private SkillLevel level = SkillLevel.Experienced;
    private SkillRequirement required = SkillRequirement.Required;
}
