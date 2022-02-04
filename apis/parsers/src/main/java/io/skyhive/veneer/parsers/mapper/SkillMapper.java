package io.skyhive.veneer.parsers.mapper;

import io.skyhive.veneer.common.ml.skill.MLSkill;
import io.skyhive.veneer.parsers.dto.skill.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Mapper
public interface SkillMapper {
    @Mapping(source = "name", target = "title")
    Skill toSkill(MLSkill skill);
}
