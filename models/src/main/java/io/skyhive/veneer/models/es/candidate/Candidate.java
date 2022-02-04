package io.skyhive.veneer.models.es.candidate;

import io.skyhive.veneer.models.es.Dictionary;
import io.skyhive.veneer.models.es.Location;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.es.Study;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author krishna
 * @created 16/12/21
 * @project skyhive-veeneer
 */
@Setter
@Getter
@Document(indexName = "employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Candidate {
    @Id
    private String id;
    @Field(type = FieldType.Object, index = true)
    private Name name;
    @Field(type = FieldType.Object, index = false)
    private ImageDto avtar;
    @Field(type = FieldType.Object)
    private Location location;
    @Field(type = FieldType.Object)
    private Dictionary jobTitle;
    @Field(type = FieldType.Nested)
    private Set<MatchingSkill> skills;
    @Field(type = FieldType.Nested)
    private List<Study> studies;
    @Field(type = FieldType.Nested, index = false)
    private List<Dictionary> hobbies;
    @Field(type = FieldType.Nested)
    private List<Experience> experience;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime registrationDate;
    @Field(type = FieldType.Text, index = false)
    private List<String> patents;
    @Field(type = FieldType.Text, index = false)
    private List<String> publications;
    @Field(type = FieldType.Object)
    private List<CoursesOrCertificates> coursesOrCertificates;
    @Field(type = FieldType.Object)
    private Preferences preferences;

    // Desired fields
    @Field(type = FieldType.Nested)
    private List<Location> desiredLocation;
    @Field(type = FieldType.Nested)
    private List<Dictionary> desiredJobTitles;
    @Field(type = FieldType.Nested)
    private Set<MatchingSkill> desiredSkills;
    @EqualsAndHashCode.Include
    @Field(type = FieldType.Keyword, index = true)
    private String email;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime dateStarted;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime dateCompleted;

    // This is for link
    @Field(type = FieldType.Keyword, index = true)
    private String enterpriseId;

    @Field(type = FieldType.Text)
    private List<String> desiredCompany;
    @Field(type = FieldType.Object)
    private Map<String, String> metadata;
}
