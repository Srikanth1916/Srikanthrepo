package io.skyhive.veneer.careerpath.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillsComparisonRequest {
	Set<Skill> from;
	Set<Skill> to;
}
