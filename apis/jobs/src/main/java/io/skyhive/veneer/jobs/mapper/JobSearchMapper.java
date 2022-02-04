package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.jobs.dto.search.JobSearchRequest;
import io.skyhive.veneer.jobs.es.JobSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = {JobMapper.class})
public interface JobSearchMapper {
    @Mapping(target="radius", source = "location.radius")
    io.skyhive.veneer.jobs.es.JobSearchRequest toEsJobSearchRequest(JobSearchRequest jobSearchRequest);
    io.skyhive.veneer.jobs.dto.search.JobSearchResponse toDtoJobSearchResponse(JobSearchResponse jobSearchResponse);
}
