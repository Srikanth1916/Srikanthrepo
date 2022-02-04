package io.skyhive.veneer.common.ml.skill;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SkillExtractionResponse {
    private List<MLSkill> skills;
}
