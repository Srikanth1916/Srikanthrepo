package io.skyhive.veneer.models.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Location {
    @Field(type = FieldType.Text, index = true)
    private String formattedAddress;
    @Field(type = FieldType.Keyword, index = true)
    private String country;
    @Field(type = FieldType.Text, index = false)
    private String locationHandle;
    @GeoPointField
    private GeoPoint location;
}
