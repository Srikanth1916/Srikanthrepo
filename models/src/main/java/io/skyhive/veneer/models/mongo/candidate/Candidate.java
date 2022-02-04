package io.skyhive.veneer.models.mongo.candidate;

import io.skyhive.veneer.models.mongo.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
@Document(collection = "employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Candidate {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private Name name;
    private ImageDto avtar;
    private Location location;
    private Dictionary jobTitle;
    private Set<MatchingSkill> skills;
    private List<Study> studies;
    private List<Dictionary> hobbies;
    private List<Experience> experience;
    private LocalDateTime registrationDate;
    private List<String> patents;
    private List<String> publications;
    private List<CoursesOrCertificates> coursesOrCertificates;
    private Preferences preferences;

    // Desired fields
    private List<Location> desiredLocation;
    private List<Dictionary> desiredJobTitles;
    private Set<MatchingSkill> desiredSkills;
    @EqualsAndHashCode.Include
    @Indexed(unique=true)
    private String email;
    // This is for link
    private String enterpriseId;
    private LocalDateTime dateStarted;
    private LocalDateTime dateCompleted;
    private List<String> desiredCompany;
    private Map<String, String> metadata;
}
