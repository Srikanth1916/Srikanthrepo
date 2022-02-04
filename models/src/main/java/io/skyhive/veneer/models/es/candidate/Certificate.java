package io.skyhive.veneer.models.es.candidate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Certificate {
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Keyword)
    private String title;

}

