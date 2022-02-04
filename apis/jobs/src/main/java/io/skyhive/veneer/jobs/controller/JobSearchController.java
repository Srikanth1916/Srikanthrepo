package io.skyhive.veneer.jobs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.services.DecryptedLocation;
import io.skyhive.veneer.common.services.DictionaryService;
import io.skyhive.veneer.common.services.LocationService;
import io.skyhive.veneer.common.services.MatchingService;
import io.skyhive.veneer.jobs.dto.search.JobSearchResponse;
import io.skyhive.veneer.jobs.dto.search.LocationRequest;
import io.skyhive.veneer.jobs.dto.search.PaginationRequest;
import io.skyhive.veneer.jobs.es.JobSearchRequest;
import io.skyhive.veneer.jobs.es.JobWithScore;
import io.skyhive.veneer.jobs.mapper.JobSearchMapper;
import io.skyhive.veneer.jobs.mapper.LocationMapper;
import io.skyhive.veneer.jobs.mapper.SkillMapper;
import io.skyhive.veneer.jobs.services.CandidateEsRepository;
import io.skyhive.veneer.jobs.services.JobEsService;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.candidate.Candidate;
import io.skyhive.veneer.models.es.job.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author krishna
 * @created 29/12/21
 * @project skyhive-veeneer
 */
@RestController
@Slf4j
public class JobSearchController {
    @Autowired
    private CandidateEsRepository candidateEsRepository;

    @Autowired
    private JobSearchMapper jobSearchMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private JobEsService jobEsService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping(value = "/search/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSearchResponse searchJobsByEmployeeId(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                    @PathVariable("id") String employeeId,
                                                    @Valid @RequestBody io.skyhive.veneer.jobs.dto.search.JobSearchRequest jobSearchRequest) throws JsonProcessingException, LocationUnidentifiedException, NotFoundException {
        log.info("Jobs Search By employee Id : {}", employeeId);
        Optional<Candidate> candidateOptional =
                candidateEsRepository.findById(employeeId);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            JobSearchRequest jobSearchRequest1 =
                    jobSearchMapper.toEsJobSearchRequest(jobSearchRequest);
            if(ObjectUtils.isEmpty(jobSearchRequest.getLocation())){
                jobSearchRequest1.setLocation(candidate.getLocation());
                jobSearchRequest1.setRadius(20);
            } else {
                if(jobSearchRequest.getLocation()!= null) {
                    jobSearchRequest1.setRadius(jobSearchRequest.getLocation().getRadius());
                } else {
                    jobSearchRequest1.setRadius(20);
                }
                jobSearchRequest1.setLocation(getLocationDetails(jobSearchRequest.getLocation()));
            }
            if(ObjectUtils.isEmpty(jobSearchRequest.getSkills())) {
                jobSearchRequest1.setSkills(candidate.getSkills());
            } else {
                jobSearchRequest1.setSkills(JobsController.setSkillsComplexity(jobSearchRequest1.getSkills(), dictionaryService));
            }
            if(ObjectUtils.isEmpty(jobSearchRequest.getJobTitles())) {
                jobSearchRequest1.setJobTitles(Arrays.asList(candidate.getJobTitle().getTitle()));
            }

            io.skyhive.veneer.jobs.es.JobSearchResponse jobSearchResponse = jobEsService
                    .searchJobs(jobSearchRequest1, jobSearchRequest.getPage(),
                            jobSearchRequest.getResultsPerPage(), enterpriseId);
            for(JobWithScore jobWithScore : jobSearchResponse.getValues()) {
                double desiredQuality =
                        MatchingService.getMatchScore(candidate.getDesiredSkills(),
                                jobWithScore.getSkills());
                jobWithScore.setDesiredQuality(desiredQuality);
            }
            return jobSearchMapper.toDtoJobSearchResponse(jobSearchResponse);

        }
        throw new NotFoundException("Employee id not found");
    }

    private Location getLocationDetails(LocationRequest locationRequest) throws LocationUnidentifiedException {
        return getLocationFromService(locationRequest, locationMapper, locationService);
    }


    static Location getLocationFromService(LocationRequest locationRequest, LocationMapper locationMapper, LocationService locationService) throws LocationUnidentifiedException {
        if(locationRequest == null){
            return null;
        }
        if(locationRequest.makeApiCall()){
            LocationDto locationDto =
                    locationMapper.toDtoLocation(locationRequest);
            DecryptedLocation decryptedLocation =
                    locationService.getLocation(locationDto);
            Location location = locationMapper.toEsLocation(locationDto);
            location.setLocation(new GeoPoint(decryptedLocation.getLatitude()
                    , decryptedLocation.getLongitude()));
            return location;
        } else {
            Location location = locationMapper.toEsLocation(locationRequest);
            location.setLocation(new GeoPoint(locationRequest.getLatitude()
                    , locationRequest.getLongitude()));
            return location;
        }
    }
}
