package io.skyhive.veneer.jobs.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Mapper
public interface GeoJsonMapper {
    default GeoPoint fromMongoGeoJson(org.springframework.data.mongodb.core.geo.GeoJsonPoint geoJsonPoint){
        return new GeoPoint(geoJsonPoint.getX(),
                geoJsonPoint.getY());
    }
}
