package io.skyhive.veneer.jobs.dto.skill;

import io.skyhive.veneer.models.es.MatchingSkill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class SkillsComparisonRequest {
	Set<MatchingSkill> from;
	Set<MatchingSkill> to;
}
