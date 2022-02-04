package io.skyhive.veneer.jobs.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.serializer.DoubleToPercentageSerializer;
import io.skyhive.veneer.jobs.dto.Job;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobWithScore extends Job {
    @JsonSerialize(using = DoubleToPercentageSerializer.class)
    private double quality;
    @JsonSerialize(using = DoubleToPercentageSerializer.class)
    private Double desiredQuality;
}
