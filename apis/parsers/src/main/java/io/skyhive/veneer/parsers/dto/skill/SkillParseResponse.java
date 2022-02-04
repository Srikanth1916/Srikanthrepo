package io.skyhive.veneer.parsers.dto.skill;

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
public class SkillParseResponse {
    private List<Skill> skills;
}
