package io.skyhive.veneer.models.es.job;

import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.WorkType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class LocationTime {
    @Field(type = FieldType.Object)
    private Location location;
    @Field(type = FieldType.Keyword)
    private WorkType workType;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime startDate;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime endDate;
    private int startHour;
    @Field(type = FieldType.Keyword)
    private Duration duration;
    @Field(type = FieldType.Integer, index = false)
    private int quantity;
}
