package io.skyhive.veneer.models.es.candidate;

import io.skyhive.veneer.models.es.JobTitleType;
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
public class Experience {
    @Field(type=FieldType.Nested)
    private JobTitleType occupation;
    @Field(type = FieldType.Text, index = true)
    private String company;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime startDate;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime endDate;
    private boolean current;
    @Field(type = FieldType.Text, index = false)
    private String description;
}
