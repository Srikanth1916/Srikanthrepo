package io.skyhive.veneer.models.es.candidate;

import io.skyhive.veneer.models.es.Compensation;
import io.skyhive.veneer.models.es.Relocate;
import io.skyhive.veneer.models.es.WorkType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Preferences {
    @Field(type= FieldType.Nested)
    private Compensation compensation;
    @Field(type= FieldType.Keyword)
    private Relocate relocate;
    @Field(type= FieldType.Keyword)
    private WorkType remote;
    @Field(type= FieldType.Binary)
    private boolean isPrivate;
}
