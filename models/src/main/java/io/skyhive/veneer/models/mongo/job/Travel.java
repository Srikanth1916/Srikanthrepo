package io.skyhive.veneer.models.mongo.job;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

/**
 * @author krishna
 * @created 25/01/22
 * @project skyhive-veeneer
 */
public enum Travel {
    None,
    Some,
    Constant;
    @Override
    @JsonValue
    public String toString() {
        return StringUtils.uncapitalize(this.name());
    }
}
