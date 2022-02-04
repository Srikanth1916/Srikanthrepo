package io.skyhive.veneer.models.es.job;

import io.skyhive.veneer.models.es.Compensation;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.mongo.JobStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;
import java.util.Set;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */

@Getter
@Setter
@Document(indexName = "job")
public class Job {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String country;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Keyword)
    private String language;
    @Field(type = FieldType.Binary)
    private boolean isConfidential;
    @Field(type = FieldType.Keyword)
    private JobType type;
    @Field(type = FieldType.Nested)
    private Set<MatchingSkill> skills;
    @Field(type = FieldType.Text, index = false)
    private String description;
    @Field(type = FieldType.Keyword)
    private String referenceCode;
    @Field(type = FieldType.Object)
    private LocationTime locationTime;
    @Field(type=FieldType.Object)
    private Compensation compensation;
    @Field(type=FieldType.Text, index = false)
    private String duties;
    @Field(type=FieldType.Text, index = false)
    private String qualification;
    @Field(type = FieldType.Keyword)
    private String enterpriseId;
    @Field(type=FieldType.Object)
    private JobStatus status;
    @Field(type=FieldType.Keyword)
    private Travel travel;
    @Field(type = FieldType.Object)
    private Map<String, String> metadata;
}
