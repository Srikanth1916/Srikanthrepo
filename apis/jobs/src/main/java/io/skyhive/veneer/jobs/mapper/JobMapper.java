package io.skyhive.veneer.jobs.mapper;

import io.skyhive.veneer.jobs.dto.Job;
import io.skyhive.veneer.jobs.es.JobWithScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Mapper(uses = {LocationTimeMapper.class, GeoJsonMapper.class, SkillMapper.class})
public interface JobMapper {
    @Mappings({
            @Mapping(target="country", source="country.twoLetterName"),
            @Mapping(target = "title", source="details.title"),
            @Mapping(target = "language", source="details.language"),
            @Mapping(target = "confidential", source="details" +
                    ".confidential"),
            @Mapping(target = "type", source="details.type"),
            @Mapping(target = "skills", source="details.skills"),
            @Mapping(target = "referenceCode", source="details.referenceCode"),
            @Mapping(target = "description", source = "details.description"),
            @Mapping(target = "travel", source = "details.travel")
    })
    io.skyhive.veneer.models.mongo.job.Job toMongoJob(Job job);
    io.skyhive.veneer.models.es.job.Job toEsJob(io.skyhive.veneer.models.mongo.job.Job job);
    @Mappings({
            @Mapping(source="country", target="country.twoLetterName"),
            @Mapping(source = "title", target="details.title"),
            @Mapping(source = "language", target="details.language"),
            @Mapping(source = "confidential", target="details" +
                    ".confidential"),
            @Mapping(source = "type", target="details.type"),
            @Mapping(source = "skills", target="details.skills"),
            @Mapping(source = "referenceCode", target="details.referenceCode"),
            @Mapping(source = "description", target = "details.description"),
            @Mapping(source="travel", target = "details.travel")
    })
    Job toDtoJob(io.skyhive.veneer.models.mongo.job.Job job);


    @Mappings({
            @Mapping(source="country", target="country.twoLetterName"),
            @Mapping(source = "title", target="details.title"),
            @Mapping(source = "language", target="details.language"),
            @Mapping(source = "confidential", target="details" +
                    ".confidential"),
            @Mapping(source = "type", target="details.type"),
            @Mapping(source = "skills", target="details.skills"),
            @Mapping(source = "referenceCode", target="details.referenceCode"),
            @Mapping(source = "description", target = "details.description"),
            @Mapping(source="travel", target = "details.travel")
    })
    Job toDtoJob(io.skyhive.veneer.models.es.job.Job job);

    @Mappings({
            @Mapping(source="country", target="country.twoLetterName"),
            @Mapping(source = "title", target="details.title"),
            @Mapping(source = "language", target="details.language"),
            @Mapping(source = "confidential", target="details" +
                    ".confidential"),
            @Mapping(source = "type", target="details.type"),
            @Mapping(source = "skills", target="details.skills"),
            @Mapping(source = "referenceCode", target="details.referenceCode"),
            @Mapping(source = "description", target = "details.description")
    })
    io.skyhive.veneer.jobs.dto.search.JobWithScore toDtoJobWithScore(JobWithScore jobWithScore);
    JobWithScore toJobWithScore(io.skyhive.veneer.models.es.job.Job job);

}
