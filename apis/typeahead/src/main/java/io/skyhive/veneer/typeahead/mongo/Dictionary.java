package io.skyhive.veneer.typeahead.mongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author krishna
 * @created 12/01/22
 * @project skyhive-veeneer
 */
@Document(value = "DictionaryEntity")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dictionary {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field("Title")
    private Title title;
    @Field("IsTool")
    private Boolean tool;
    @Field("IsLanguage")
    private Boolean language;
    @Field("Definition")
    private Title definition;
}
