package io.skyhive.veneer.models.es;

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
public class Study {
    private Dictionary institution;
    private Dictionary areaOfStudy;
    private Dictionary degree;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second,
            storeNullValue = false)
    private LocalDateTime startDate;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, storeNullValue = false)
    private LocalDateTime endDate;
    private boolean isStudyNotCompleted;
    private boolean current;
}
