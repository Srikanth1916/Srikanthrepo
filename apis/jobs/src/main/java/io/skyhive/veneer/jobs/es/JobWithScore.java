package io.skyhive.veneer.jobs.es;

import io.skyhive.veneer.models.es.job.Job;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 28/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobWithScore extends Job {
    private Double quality;
    private Double desiredQuality;
}
