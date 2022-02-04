package io.skyhive.veneer.models.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Location {
    private String formattedAddress;
    private String country;
    private String locationHandle;
    private GeoJsonPoint location;
}
