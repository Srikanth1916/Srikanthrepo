package io.skyhive.veneer.jobs.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobSearchResponse {
    private long total;
    private List<JobWithScore> values;
}
