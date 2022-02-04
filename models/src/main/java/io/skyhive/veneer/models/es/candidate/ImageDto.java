package io.skyhive.veneer.models.es.candidate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * The type Image dto.
 */
@Getter
@Setter
public class ImageDto {
    @Field(type = FieldType.Text, index = false)
    private String url;
    @Field(type = FieldType.Text, index = false)
    private String imageHandle;
}
