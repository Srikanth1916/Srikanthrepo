package io.skyhive.veneer.models.mongo;

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
public class Study {
    private Dictionary institution;
    private Dictionary areaOfStudy;
    private Dictionary degree;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean isStudyNotCompleted;
    private boolean current;
}
