package io.skyhive.veneer.models.es.candidate;

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
public class Name {
    @Field(type = FieldType.Text)
    private String firstname;
    @Field(type = FieldType.Text)
    private String lastname;
}
