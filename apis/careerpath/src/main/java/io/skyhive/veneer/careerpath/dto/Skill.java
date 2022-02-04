package io.skyhive.veneer.careerpath.dto;

import io.skyhive.veneer.models.es.SkillLevel;
import io.skyhive.veneer.models.mongo.SkillRequirement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill {

	@EqualsAndHashCode.Include
	private String id;
	private String title;
	private SkillLevel level = SkillLevel.Experienced;
	private SkillRequirement required = SkillRequirement.Required;

}
