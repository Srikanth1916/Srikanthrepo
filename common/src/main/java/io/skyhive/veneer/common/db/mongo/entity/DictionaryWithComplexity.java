package io.skyhive.veneer.common.db.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author krishna
 * @created 21/12/21
 * @project skyhive-veeneer
 */
@Setter
@Getter
@Document("DictionaryEntity")
public class DictionaryWithComplexity {
    private static final DictionaryWithComplexity defaultDictionary =
            new DictionaryWithComplexity();
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @Field(name = "Complexity")
    private int complexity;
    @Field(name = "IsLocked")
    private boolean isLocked;
    @Field(name = "searchCount")
    private int searchCount;

    public static DictionaryWithComplexity getDefaultDictionary() {
        defaultDictionary.setComplexity(4);
        return defaultDictionary;
    }
}