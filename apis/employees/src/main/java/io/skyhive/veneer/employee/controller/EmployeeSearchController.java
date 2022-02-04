package io.skyhive.veneer.employee.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.common.services.DecryptedLocation;
import io.skyhive.veneer.common.services.DictionaryService;
import io.skyhive.veneer.common.services.LocationService;
import io.skyhive.veneer.common.services.MatchingService;
import io.skyhive.veneer.employee.dto.search.EmployeeJobSkillsMatchPercentageResponse;
import io.skyhive.veneer.employee.dto.search.EmployeeSearchRequest;
import io.skyhive.veneer.employee.dto.search.EmployeeSearchResponse;
import io.skyhive.veneer.employee.dto.search.LocationRequest;
import io.skyhive.veneer.employee.dto.search.PaginationRequest;
import io.skyhive.veneer.employee.mapper.EmployeeSearchMapper;
import io.skyhive.veneer.employee.mapper.LocationMapper;
import io.skyhive.veneer.employee.mapper.SkillMatchPercentageMapper;
import io.skyhive.veneer.employee.services.CandidateEsServiceImpl;
import io.skyhive.veneer.employee.services.JobEsRepository;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.es.job.Job;
import lombok.extern.slf4j.Slf4j;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@RestController
@Slf4j
public class EmployeeSearchController {

    @Autowired
    private EmployeeSearchMapper employeeSearchMapper;

    @Autowired
    private CandidateEsServiceImpl candidateEsService;

    @Autowired
    private JobEsRepository jobEsRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private SkillMatchPercentageMapper skillMatchPercentageMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeSearchResponse searchEmployees(@Schema(hidden = true) @RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                  @Valid @RequestBody EmployeeSearchRequest employeeSearchRequest)
            throws JsonProcessingException, LocationUnidentifiedException {
        io.skyhive.veneer.employee.es.EmployeeSearchRequest employeeSearchRequest1 = employeeSearchMapper
                .toEsEmployeeSearchRequest(employeeSearchRequest);
        employeeSearchRequest1.setSkills(setSkillsComplexity(employeeSearchRequest1.getSkills()));
        employeeSearchRequest1.setLocation(getLocationDetails(employeeSearchRequest.getLocation()));
        io.skyhive.veneer.employee.es.EmployeeSearchResponse employeeSearchResponse = candidateEsService
                .searchCandidates(employeeSearchRequest1, employeeSearchRequest.getPage(),
                        employeeSearchRequest.getResultsPerPage(), enterpriseId);
        return employeeSearchMapper.toDtoEmployeeSearchResponse(employeeSearchResponse);
    }

    @PostMapping(value = "/search/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeSearchResponse searchEmployeesByJobId(@Schema(hidden =
            true) @RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                         @PathVariable("id") String jobId,
                                                         @Valid @RequestBody PaginationRequest paginationRequest)
            throws JsonProcessingException, NotFoundException {
        log.info("Employees Search By Job Id : {}", jobId);
        Optional<Job> job = jobEsRepository.findById(jobId);
        if (job.isPresent() && enterpriseId.equals(job.get().getEnterpriseId())) {
            Job job1 = job.get();
            io.skyhive.veneer.employee.es.EmployeeSearchRequest employeeSearchRequest = new io.skyhive.veneer.employee.es.EmployeeSearchRequest();
            employeeSearchRequest
                    .setSkills(job1.getSkills());
            if(!paginationRequest.getIgnoreJobTitle()) {
                employeeSearchRequest.setJobTitles(Arrays.asList(job1.getTitle()));
            }
            if(!paginationRequest.getIgnoreLocation()){
                employeeSearchRequest.setLocation(job1.getLocationTime().getLocation());
                employeeSearchRequest.setRadius(paginationRequest.getRadius());
            }
            employeeSearchRequest.setMinMatchPercentage(paginationRequest.getMinimumMatchPercentage());
            io.skyhive.veneer.employee.es.EmployeeSearchResponse employeeSearchResponse = candidateEsService
                    .searchCandidates(employeeSearchRequest, paginationRequest.getPage(),
                            paginationRequest.getResultsPerPage(), enterpriseId);
            return employeeSearchMapper.toDtoEmployeeSearchResponse(employeeSearchResponse);

        } else {
            throw new NotFoundException("JobId not found");
        }
    }

    @PostMapping(value = "/{empId}/job/{jobId}", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public EmployeeJobSkillsMatchPercentageResponse matchEmployeeToJob(@Schema(hidden = true) @RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                                       @PathVariable("empId") String empId,
                                                                       @PathVariable("jobId") String jobId) throws NotFoundException {
        log.info("Employee Id: {}, Job Id : {}", empId, jobId);
        io.skyhive.veneer.models.es.candidate.Candidate candidate =
                candidateEsService.findById(empId, enterpriseId);
        Set<io.skyhive.veneer.models.es.MatchingSkill> empSkills;
        Set<io.skyhive.veneer.models.es.MatchingSkill> empDesiredSkills;
        empSkills = candidate.getSkills();
        empDesiredSkills = candidate.getDesiredSkills();
        log.info("Emp Skills : {}", empSkills.size());

        Optional<io.skyhive.veneer.models.es.job.Job> job = jobEsRepository.findById(jobId);
        Set<MatchingSkill> jobSkills;
        if(job.isPresent()) {
            jobSkills = job.get().getSkills();
            log.info("Job Skills : {}", jobSkills.size());
        } else {
            throw new NotFoundException("JobId not found");
        }
        EmployeeJobSkillsMatchPercentageResponse response =
                new EmployeeJobSkillsMatchPercentageResponse();
        SkillsMatchPercentageResponse skillsMatchPercentageResponse =
                MatchingService.caliculateMatchPercentage(empSkills, jobSkills);
        response.setNormal(skillMatchPercentageMapper.toSkillMatchPercentage(skillsMatchPercentageResponse));
        if(!CollectionUtils.isEmpty(empDesiredSkills)) {
            SkillsMatchPercentageResponse desiredSkillMatchPercentageResponse =
                    MatchingService.caliculateMatchPercentage(candidate.getDesiredSkills(),
                            jobSkills);
            response.setDesired(skillMatchPercentageMapper.toSkillMatchPercentage(desiredSkillMatchPercentageResponse));
        }
        return response;
    }

    private Set<MatchingSkill> setSkillsComplexity(Set<MatchingSkill> matchingSkills) {
        if (!CollectionUtils.isEmpty(matchingSkills)) {
            DictionaryWithComplexity defaultDictionary = new DictionaryWithComplexity();
            defaultDictionary.setComplexity(4);
            Set<String> skillIds = matchingSkills.stream().map(skill -> skill.getId()).collect(Collectors.toSet());
            Map<String, DictionaryWithComplexity> dictionaryWithComplexityMap = dictionaryService.getByIds(skillIds);
            matchingSkills.forEach(matchingSkill -> matchingSkill.setComplexity(dictionaryWithComplexityMap
                    .getOrDefault(matchingSkill.getId(), defaultDictionary).getComplexity()));
            return matchingSkills;
        }
        return null;
    }

    private Location getLocationDetails(LocationRequest locationRequest)
            throws LocationUnidentifiedException {
        if (locationRequest == null) {
            return null;
        }
        if (locationRequest.makeApiCall()) {
            LocationDto locationDto = locationMapper.toDtoLocation(locationRequest);
            DecryptedLocation decryptedLocation = locationService.getLocation(locationDto);
            Location location = locationMapper.toEsLocation(locationDto);
            location.setLocation(new GeoPoint(decryptedLocation.getLatitude(), decryptedLocation.getLongitude()));
            return location;
        } else {
            Location location = locationMapper.toEsLocation(locationRequest);
            location.setLocation(new GeoPoint(locationRequest.getLatitude(), locationRequest.getLongitude()));
            return location;
        }
    }

}
