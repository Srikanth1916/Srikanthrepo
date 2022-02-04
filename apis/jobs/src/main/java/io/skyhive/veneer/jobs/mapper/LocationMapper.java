package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.jobs.dto.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = GeoJsonMapper.class)
public interface LocationMapper {
    io.skyhive.veneer.models.es.Location toEsLocation(Location location);
    LocationDto toDtoLocation(Location location);
    io.skyhive.veneer.models.es.Location toEsLocation(LocationDto location);
}
