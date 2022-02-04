package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author krishna
 * @created 24/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobStatus {
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime dateTime = LocalDateTime.now();
    private JobState state = JobState.Published;
}
