package io.skyhive.veneer.models.mongo;

import io.skyhive.veneer.models.mongo.job.Duration;
import io.skyhive.veneer.models.mongo.job.PayType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Compensation {
    private PayType type;
    private Double payRate;
    private String currency;
    private Duration duration;
    private int quantity;
    private int hoursPerWeek;
    private Boolean benefits;
    private Boolean hidden;
    private Relocate relocate;
}
