package io.skyhive.veneer.models.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class MatchingSkill extends Dictionary {
    @Field(type = FieldType.Integer, index = false)
    private int complexity = 4;
    @Field(type = FieldType.Keyword, index = false)
    private SkillLevel level = SkillLevel.Experienced;
    @Field(type = FieldType.Keyword, index = false)
    private SkillRequirement required = SkillRequirement.Required;
}
