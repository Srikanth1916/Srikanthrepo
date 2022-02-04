package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.jobs.dto.skill.ClusteredSkill;
import io.skyhive.veneer.models.es.MatchingSkill;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface MatchingSkillMapper {
	Set<MatchingSkill> toEsMatchingSkill(List<io.skyhive.veneer.models.mongo.MatchingSkill> set);
	Set<MatchingSkill> toMatchingSkill(List<ClusteredSkill>
			clusteredSkillList);
}
