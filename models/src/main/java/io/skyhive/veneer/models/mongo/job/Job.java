package io.skyhive.veneer.models.mongo.job;

import io.skyhive.veneer.models.mongo.Compensation;
import io.skyhive.veneer.models.mongo.JobStatus;
import io.skyhive.veneer.models.mongo.MatchingSkill;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Map;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */

@Getter
@Setter
@Document(collection = "job")
public class Job {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String country;
    private String title;
    private String language;
    private boolean isConfidential;
    private JobType type;
    private List<MatchingSkill> skills;
    private String description;
    @Indexed(unique = true)
    private String referenceCode;
    private LocationTime locationTime;
    private Compensation compensation;
    private String duties;
    private String qualification;
    private String enterpriseId;
    private JobStatus status;
    private Travel travel;
    private Map<String, String> metadata;
}
