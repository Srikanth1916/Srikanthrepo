package io.skyhive.veneer.jobs.es;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSearchResponse {
    private long total;
    private List<JobWithScore> values;
}
