package io.skyhive.veneer.models.es;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 24/01/22
 * @project skyhive-veeneer
 */
public enum JobState {
    Cancelled(-1),
    None(0),
    Draft(1),
    Published(2),
    Hired(3);
    int value;
    JobState(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
