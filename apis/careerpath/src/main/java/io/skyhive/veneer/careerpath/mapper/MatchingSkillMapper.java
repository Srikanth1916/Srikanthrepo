package io.skyhive.veneer.careerpath.mapper;

import java.util.List;
import java.util.Set;

import io.skyhive.veneer.careerpath.dto.skills.ClusteredSkill;
import org.mapstruct.Mapper;

import io.skyhive.veneer.careerpath.dto.Skill;
import io.skyhive.veneer.models.es.MatchingSkill;

@Mapper
public interface MatchingSkillMapper {
	Set<MatchingSkill> toMatchingSkill(Set<Skill> set);
	Set<MatchingSkill> toMatchingSkill(List<ClusteredSkill>
			clusteredSkillList);
}
