package io.skyhive.veneer.employee.controller;

import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.exception.LocationUnidentifiedException;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.services.DecryptedLocation;
import io.skyhive.veneer.common.services.DictionaryService;
import io.skyhive.veneer.common.services.LocationService;
import io.skyhive.veneer.employee.dto.Employee;
import io.skyhive.veneer.employee.dto.Location;
import io.skyhive.veneer.employee.mapper.EmployeeMapper;
import io.skyhive.veneer.employee.mapper.LocationMapper;
import io.skyhive.veneer.employee.services.CandidateEsServiceImpl;
import io.skyhive.veneer.employee.services.CandidateMongoService;
import io.skyhive.veneer.models.mongo.candidate.Candidate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Employee controller.
 */
@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    private CandidateMongoService candidateService;
    @Autowired
    private CandidateEsServiceImpl candidateEsService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * @param employee
     * @return
     */

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                 @Valid @RequestBody Employee employee)
            throws LocationUnidentifiedException {
        Candidate can = employeeMapper.fromEsEmployee(employee);
        can.setEnterpriseId(enterpriseId);
        return saveEmployeeSkillsWithComplexity(employee, can);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                   @PathVariable("id") String id,
                                   @Valid @RequestBody Employee employee)
            throws LocationUnidentifiedException, NotFoundException {
        employee.setEmployeeId(id);
        Candidate can = employeeMapper.fromEsEmployee(employee);
        can.setEnterpriseId(enterpriseId);
        if (!candidateService.existsById(id, enterpriseId)) {
            throw new NotFoundException("Given id not found");
        }
        return saveEmployeeSkillsWithComplexity(employee, can);
    }

    @GetMapping(value = "{id}")
    public Employee getEmployee(@Schema(hidden = true) @RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                @PathVariable("id") String id) throws NotFoundException {
        Candidate candidate = candidateService.findById(id, enterpriseId);
        return employeeMapper.toDtoEmployee(candidate);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Schema(hidden = true) @RequestHeader(value =
            "X-Enterprise-Id") String enterpriseId,
                               @PathVariable("id") String id) throws NotFoundException {
        Candidate candidate = candidateService.findById(id, enterpriseId);
        candidateService.delete(candidate);
        io.skyhive.veneer.models.es.candidate.Candidate candidateEs =
                employeeMapper.fromMongoCandidate(candidate);
        candidateEsService.delete(candidateEs);
    }

    private Employee saveEmployeeSkillsWithComplexity(Employee employee, Candidate can)
            throws LocationUnidentifiedException {
        if (!CollectionUtils.isEmpty(employee.getSkills())) {
            DictionaryWithComplexity defaultDictionary = new DictionaryWithComplexity();
            defaultDictionary.setComplexity(4);
            Set<String> skillIds = employee.getSkills().stream().map(skill -> skill.getId())
                    .collect(Collectors.toSet());
            Map<String, DictionaryWithComplexity> dictionaryWithComplexityMap = dictionaryService.getByIds(skillIds);
            can.getSkills().forEach(matchingSkill -> matchingSkill.setComplexity(dictionaryWithComplexityMap
                    .getOrDefault(matchingSkill.getId(), defaultDictionary).getComplexity()));
        }
        GeoJsonPoint geoJsonPoint = getDecryptedLocation(employee.getLocation());
        can.getLocation()
                .setLocation(geoJsonPoint);
        if (employee.getDesiredLocation() != null) {
            List<io.skyhive.veneer.models.mongo.Location> desiredLocations =
                    new LinkedList<>();
            for (Location location : employee.getDesiredLocation()) {
                GeoJsonPoint geoJsonPoint1 = getDecryptedLocation(location);
                io.skyhive.veneer.models.mongo.Location location1 = locationMapper.toMongoLocation(location);
                location1.setLocation(geoJsonPoint1);
                desiredLocations.add(location1);
            }
            can.setDesiredLocation(desiredLocations);
        }
        can = candidateService.save(can);
        candidateEsService.save(employeeMapper.fromMongoCandidate(can));
        return employeeMapper.toDtoEmployee(can);
    }

    private GeoJsonPoint getDecryptedLocation(Location location) throws LocationUnidentifiedException {
        LocationDto locationDto = locationMapper.toDtoLocation(location);
        if (ObjectUtils.isEmpty(locationDto.getFormattedAddress())) {
            locationDto.setFormattedAddress(location.getFullAddress());
            locationDto.setCountry(location.getCountry());
        }
        DecryptedLocation decryptedLocation = locationService.getLocation(locationDto);
        return new GeoJsonPoint(decryptedLocation.getLatitude(),
                decryptedLocation.getLongitude());
    }

}
