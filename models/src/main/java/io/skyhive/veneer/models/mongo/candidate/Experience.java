package io.skyhive.veneer.models.mongo.candidate;

import io.skyhive.veneer.models.mongo.JobTitleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Experience {
    private JobTitleType occupation;
    private String company;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean current;
    private String description;
}
