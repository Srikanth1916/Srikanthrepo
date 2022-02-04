package io.skyhive.veneer.models.es;

import io.skyhive.veneer.models.es.job.Duration;
import io.skyhive.veneer.models.es.job.PayType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Compensation {
    @Field(type = FieldType.Keyword)
    private PayType type;
    @Field(type = FieldType.Double)
    private Double payRate;
    @Field(type = FieldType.Keyword)
    private String currency;
    @Field(type = FieldType.Keyword)
    private Duration duration;
    @Field(type = FieldType.Integer)
    private int quantity;
    @Field(type = FieldType.Integer)
    private int hoursPerWeek;
    @Field(type = FieldType.Binary)
    private Boolean benefits;
    @Field(type = FieldType.Binary)
    private Boolean hidden;
    @Field(type = FieldType.Keyword)
    private Relocate relocate;
}
