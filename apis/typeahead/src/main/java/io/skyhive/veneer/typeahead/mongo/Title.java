package io.skyhive.veneer.typeahead.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

/**
 * @author krishna
 * @created 12/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Title {
    @Field("Title")
    private Map<String, String> title;
}
