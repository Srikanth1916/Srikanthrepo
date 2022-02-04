package io.skyhive.veneer.models.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 24/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobStatus {
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime dateTime;
    @Field(type = FieldType.Keyword)
    private JobState state;
}
