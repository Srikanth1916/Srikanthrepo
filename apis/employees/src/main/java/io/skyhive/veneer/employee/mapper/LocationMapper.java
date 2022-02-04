package io.skyhive.veneer.employee.mapper;

import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.models.es.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses= GeoJsonMapper.class)
public interface LocationMapper {
    @Mapping(target = "formattedAddress", source = "formattedAddress", defaultExpression = "java(location" +
            ".getFullAddress())")
    Location toEsLocation(io.skyhive.veneer.employee.dto.Location location);
    Location toEsLocation(LocationDto location);
    @Mapping(target = "formattedAddress", source = "formattedAddress", defaultExpression = "java(location" +
            ".getFullAddress())")
    io.skyhive.veneer.models.mongo.Location toMongoLocation(io.skyhive.veneer.employee.dto.Location location);
    Location fromMongoLocation(io.skyhive.veneer.models.mongo.Location location);
    io.skyhive.veneer.employee.dto.Location toDtoLocation(LocationDto location);
    @Mapping(target = "formattedAddress", source = "formattedAddress", defaultExpression = "java(location" +
            ".getFullAddress())")
    LocationDto toDtoLocation(io.skyhive.veneer.employee.dto.Location location);
}
