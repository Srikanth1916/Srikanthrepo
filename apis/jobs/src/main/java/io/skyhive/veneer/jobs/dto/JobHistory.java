package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The type Job history.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class JobHistory {
    private String state;
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime date;

}
