package io.skyhive.veneer.employee.es;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.MatchingSkill;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * @author krishna
 * @created 23/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class EmployeeSearchRequest {
    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private int resultsPerPage;
    private int page;
    private Set<MatchingSkill> skills;
    private List<String> jobTitles;
    private LocalDateTime hiredFrom;
    private LocalDateTime hiredTo;
    private String degree;
    private String country;
    private Location location;
    private int radius;
    private float minMatchPercentage;
    private CompanyDetails companyDetails;
    private List<String> desiredJobTitles;
    private String university;
    @JsonIgnore
    public String getHiredFromAsString() {
        if(hiredFrom == null) return null;
        return hiredFrom.format(formatter);
    }
    @JsonIgnore
    public String getHiredToAsString() {
        if(hiredTo == null) return null;
        return hiredTo.format(formatter);
    }


}
