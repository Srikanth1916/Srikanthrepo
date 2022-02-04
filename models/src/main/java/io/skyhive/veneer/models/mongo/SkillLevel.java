package io.skyhive.veneer.models.mongo;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum SkillLevel {
    /**
     * Entry skill level.
     */
    Entry(1),
    /**
     * Intermediate skill level.
     */
    Intermediate(2),
    /**
     * Experienced skill level.
     */
    Experienced(3);

    int score;
    SkillLevel(int score){
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
