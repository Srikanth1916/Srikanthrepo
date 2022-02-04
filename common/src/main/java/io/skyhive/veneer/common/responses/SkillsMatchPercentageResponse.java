package io.skyhive.veneer.common.responses;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.serializer.DoubleToPercentageSerializer;
import io.skyhive.veneer.models.es.MatchingSkill;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SkillsMatchPercentageResponse {
	private Set<MatchingSkill> matchingSkills;
	private Set<MatchingSkill> missingSkills;
	@JsonSerialize(using = DoubleToPercentageSerializer.class)
	private double quality;
}
