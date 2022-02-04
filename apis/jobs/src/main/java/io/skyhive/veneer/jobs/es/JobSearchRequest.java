package io.skyhive.veneer.jobs.es;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.skyhive.veneer.jobs.dto.JobState;
import io.skyhive.veneer.jobs.dto.JobType;
import io.skyhive.veneer.jobs.dto.PayType;
import io.skyhive.veneer.jobs.dto.Travel;
import io.skyhive.veneer.jobs.dto.search.JobSearchSortField;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.es.WorkType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobSearchRequest {
    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private List<String> jobTitles;
    private Set<MatchingSkill> skills;
    private Location location;
    private String country;
    private long radius;
    private float minMatchPercentage;
    private WorkType workType;
    private LocalDateTime fromPostedDate;
    private LocalDateTime toPostedDate;
    private PayType payType;
    private Double minPayRate;
    private JobType type;
    private JobSearchSortField sortBy;
    private Travel travel;
    private JobState state;
    

    @JsonIgnore
    public String getFromPostedDateAsString() {
        if(fromPostedDate == null) return null;
        return fromPostedDate.format(formatter);
    }
    @JsonIgnore
    public String getToPostedDateAsString() {
        if(toPostedDate == null) return null;
        return toPostedDate.format(formatter);
    }
}
