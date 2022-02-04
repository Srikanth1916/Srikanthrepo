package io.skyhive.veneer.jobs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.services.DecryptedLocation;
import io.skyhive.veneer.common.services.DictionaryService;
import io.skyhive.veneer.common.services.LocationService;
import io.skyhive.veneer.jobs.dto.Job;
import io.skyhive.veneer.jobs.dto.search.LocationRequest;
import io.skyhive.veneer.jobs.es.JobSearchRequest;
import io.skyhive.veneer.jobs.es.JobSearchResponse;
import io.skyhive.veneer.jobs.mapper.JobMapper;
import io.skyhive.veneer.jobs.mapper.JobSearchMapper;
import io.skyhive.veneer.jobs.mapper.LocationMapper;
import io.skyhive.veneer.jobs.services.JobEsService;
import io.skyhive.veneer.jobs.services.JobMongoService;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.MatchingSkill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.skyhive.veneer.jobs.controller.JobSearchController.getLocationFromService;

/**
 * The type Jobs controller.
 */
@RestController
@Slf4j
public class JobsController {

    @Autowired
    private JobMongoService jobService;

    @Autowired
    private JobEsService jobEsService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobSearchMapper jobSearchMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * Create job jobs.
     *
     * @param job the job
     * @return the jobs
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                         @Valid @RequestBody Job job)
            throws LocationUnidentifiedException {
        return fillSkillsAndLocation(job, enterpriseId);
    }

    @PutMapping(value = "/{id}")
    public Job updateJob(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                         @PathVariable("id") String id,
                         @Valid @RequestBody Job job) throws LocationUnidentifiedException, NotFoundException {
        job.setId(id);
        if(jobService.existsById(id, enterpriseId)) {
            return fillSkillsAndLocation(job, enterpriseId);
        }
        throw new NotFoundException("Given id not found");
    }

    private Job fillSkillsAndLocation(Job job, String enterpriseId) throws LocationUnidentifiedException {
        io.skyhive.veneer.models.mongo.job.Job jobMapped = jobMapper.toMongoJob(job);
        jobMapped.setEnterpriseId(enterpriseId);
        if(!CollectionUtils.isEmpty(jobMapped.getSkills())) {
            DictionaryWithComplexity defaultDictionary =
                    new DictionaryWithComplexity();
            defaultDictionary.setComplexity(4);
            Set<String> skillIds =
                    jobMapped.getSkills().stream().map(skill -> skill.getId()).collect(Collectors.toSet());
            Map<String, DictionaryWithComplexity> dictionaryWithComplexityMap =
                    dictionaryService.getByIds(skillIds);
            jobMapped.getSkills().forEach(matchingSkill ->
                    matchingSkill.setComplexity(dictionaryWithComplexityMap.getOrDefault(matchingSkill.getId(), defaultDictionary).getComplexity()));
        }
        LocationDto locationDto =
                locationMapper.toDtoLocation(job.getLocationTime().getLocation());
        if(ObjectUtils.isEmpty(locationDto.getFormattedAddress())) {
            locationDto.setFormattedAddress(job.getLocationTime().getLocation().getFullAddress());
            locationDto.setCountry(job.getLocationTime().getLocation().getCountry());
        }
        DecryptedLocation decryptedLocation =
                locationService.getLocation(locationDto);
        jobMapped.getLocationTime().getLocation().setLocation(new GeoJsonPoint(decryptedLocation.getLatitude(),decryptedLocation.getLongitude()));
        io.skyhive.veneer.models.mongo.job.Job savedJob =jobService.save(jobMapped);
        jobEsService.save(jobMapper.toEsJob(savedJob));
        return jobMapper.toDtoJob(savedJob);
    }

    @GetMapping(value = "/{id}")
    public Job getJob(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                      @PathVariable("id") String id) throws NotFoundException {
        io.skyhive.veneer.models.mongo.job.Job tempJob =
                new io.skyhive.veneer.models.mongo.job.Job();
        tempJob.setId(id);
        io.skyhive.veneer.models.mongo.job.Job job =
                jobService.findById(id, enterpriseId);
        return jobMapper.toDtoJob(job);
    }

    @PostMapping(value = "/search")
    public io.skyhive.veneer.jobs.dto.search.JobSearchResponse searchJobs(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                                          @Valid @RequestBody io.skyhive.veneer.jobs.dto.search.JobSearchRequest jobSearchRequest
                                                                          )
            throws JsonProcessingException, LocationUnidentifiedException {
        JobSearchRequest jobSearchRequest1 =
                jobSearchMapper.toEsJobSearchRequest(jobSearchRequest);
        jobSearchRequest1.setSkills(setSkillsComplexity(jobSearchRequest1.getSkills(), dictionaryService));
        jobSearchRequest1.setLocation(getLocationDetails(jobSearchRequest.getLocation()));
         JobSearchResponse jobSearchResponse =
                jobEsService.searchJobs(jobSearchRequest1,
                        jobSearchRequest.getPage(),
                        jobSearchRequest.getResultsPerPage(), enterpriseId);
        return jobSearchMapper.toDtoJobSearchResponse(jobSearchResponse);
    }
    /**
     * Delete job.
     *
     * @param id the id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                          @PathVariable("id") String id) throws NotFoundException {
        io.skyhive.veneer.models.mongo.job.Job job =
                jobService.findById(id, enterpriseId);
        jobService.delete(job);
        jobEsService.delete(jobMapper.toEsJob(job));
    }

    private Location getLocationDetails(LocationRequest locationRequest) throws LocationUnidentifiedException {
        return getLocationFromService(locationRequest, locationMapper, locationService);
    }

    static Set<MatchingSkill> setSkillsComplexity(Set<MatchingSkill> matchingSkills, DictionaryService dictionaryService) {
        if(!CollectionUtils.isEmpty(matchingSkills)) {
            DictionaryWithComplexity defaultDictionary =
                    new DictionaryWithComplexity();
            defaultDictionary.setComplexity(4);
            Set<String> skillIds =
                    matchingSkills.stream().map(skill -> skill.getId()).collect(Collectors.toSet());
            Map<String, DictionaryWithComplexity> dictionaryWithComplexityMap =
                    dictionaryService.getByIds(skillIds);
            matchingSkills.forEach(matchingSkill ->
                    matchingSkill.setComplexity(dictionaryWithComplexityMap.getOrDefault(matchingSkill.getId(), defaultDictionary).getComplexity()));
            return matchingSkills;
        }
        return null;
    }
}
