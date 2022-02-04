package io.skyhive.veneer.models.es;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum SkillRequirement {
    /**
     * Required skill requirement.
     */
    Required(2),
    /**
     * Optional skill requirement.
     */
    Optional(1);
    int score;
    SkillRequirement(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
