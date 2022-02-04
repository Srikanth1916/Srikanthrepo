package io.skyhive.veneer.models.es;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
public enum WorkType {
    Remote, Onsite, Hybrid;
    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}