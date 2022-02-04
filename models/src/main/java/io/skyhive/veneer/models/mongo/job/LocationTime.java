package io.skyhive.veneer.models.mongo.job;

import io.skyhive.veneer.models.mongo.Location;
import io.skyhive.veneer.models.mongo.WorkType;
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
public class LocationTime {
    private Location location;
    private WorkType workType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int startHour;
    private Duration duration;
    private int quantity;
}
