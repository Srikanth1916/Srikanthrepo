package io.skyhive.veneer.models.es;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dictionary {
    @Field(type = FieldType.Keyword)
    @EqualsAndHashCode.Include
    private String id;
    @Field(type = FieldType.Keyword)
    private String title;
}
