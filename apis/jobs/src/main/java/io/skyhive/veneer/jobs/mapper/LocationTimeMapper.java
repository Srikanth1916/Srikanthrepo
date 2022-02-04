package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.jobs.dto.LocationTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = {LocationMapper.class, GeoJsonMapper.class})
public interface LocationTimeMapper {
   io.skyhive.veneer.models.es.job.LocationTime toEsLocationTime(LocationTime locationTime);
}