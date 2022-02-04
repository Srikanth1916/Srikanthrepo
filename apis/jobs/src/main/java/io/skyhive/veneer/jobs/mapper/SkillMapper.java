package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.jobs.dto.SkillWithRequirement;
import io.skyhive.veneer.models.mongo.MatchingSkill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@Mapper
public interface SkillMapper {
    @Mappings({
            @Mapping(source = "requirement", target = "required")
    })
    MatchingSkill fromSkillRequirement(SkillWithRequirement skillWithRequirement);
    @Mappings({
            @Mapping(source = "requirement", target = "required")
    })
    io.skyhive.veneer.models.es.MatchingSkill toEsMatchingSkill(SkillWithRequirement skillWithRequirement);
    @Mappings({
            @Mapping(target = "requirement", source = "required")
    })
    SkillWithRequirement toDtoMatchingSkill(MatchingSkill matchingSkill);
    @Mappings({
            @Mapping(target = "requirement", source = "required")
    })
    SkillWithRequirement toDtoMatchingSkill(io.skyhive.veneer.models.es.MatchingSkill matchingSkill);

}
