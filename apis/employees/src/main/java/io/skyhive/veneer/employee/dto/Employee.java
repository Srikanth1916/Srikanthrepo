package io.skyhive.veneer.employee.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.skyhive.veneer.common.annotation.CompareDate;
import io.skyhive.veneer.common.serializer.MultiLocalDateTimeDeSerializer;
import io.skyhive.veneer.common.serializer.SkyhiveLocalDateTimeSerializer;
import io.skyhive.veneer.common.validator.SHLimitedList;
import io.skyhive.veneer.common.validator.SHLimitedMap;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Employee.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompareDate(before = "dateStarted", after="dateCompleted", message =
        "dateStarted must be lower than the dateCompleted")
public class Employee {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String employeeId;
    @Schema(description = "Name specifies name of an employee")
    @NotNull
    @Valid
    private Name name;
    @Schema(description = "Avatar specifies profile picture of an employee")
    private Image avatar;
    @Schema(description = "AccountInformation specifies contact details of an employee")
    @NotNull
    @Valid
    private AccountInformation accountInformation;
    @Schema(description = "Location specifies location of an employee ")
    @NotNull
    @Valid
    private Location location;
    @Schema(description = "jobTitle specifies the title of employee's Job")
    @NotNull(message = "Job Title is mandatory")
    @Valid
    private JobTitle jobTitle;
    @Schema(description = "skills specifies the skill set that the employee posses")
    @NotNull(message = "Skills is mandatory")
    @Valid
    @NotEmpty(message="Skills cant be empty")
    private Set<Skill> skills;
    @Schema(description = "roles specifies the set of roles of an employee")
    private List<String> roles;
    @Schema(description = "registrationDate specifies date of registration of respective employee")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    @PastOrPresent(message = "Registration date cannot be a future date")
    private LocalDateTime registrationDate =  LocalDateTime.now();
    @Schema(description = "preferences specifies employee's preferences such as work type,compensations etc")
    private Preferences preferences;
    @Schema(description = "studies specifies education background of an employee")
    private List<Study> studies;
    @Schema(description = "trainings specifies trainings that the employee has taken")
    private List<Object> trainings;
    @Schema(description = "licenses specifies the certifications of an employee")
    private List<Object> licenses;
    @Schema(description = "CoursesOrCertificates specifies the courses in which employee is certified")
    private List<CoursesOrCertificates> coursesOrCertificates;
    @Schema(description = "patents specifies details of employee's parents")
    private String[] patents;
    @Schema(description = "publications specifies publications of an employee if any")
    private String[] publications;
    @Schema(description = "hobbies specifies hobbies of an employee")
    private List<Hobby> hobbies;
    @Schema(description = "experience specifies experience of an employee")
    private List<Experience> experience;
    @Schema(description = "dateStarted specifies the date of joining of respective employee")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime dateStarted;
    @Schema(description = "dateCompleted specifies last working day of an employee")
    @JsonDeserialize(using = MultiLocalDateTimeDeSerializer.class)
    @JsonSerialize(using = SkyhiveLocalDateTimeSerializer.class)
    private LocalDateTime dateCompleted;
    @Schema(description = "Desired Locations information of an employee")
    private SHLimitedList<Location> desiredLocation;
    @Schema(description = "Desired JobTitle information of an employee")
    private SHLimitedList<JobTitle> desiredJobTitles;
    @Schema(description = "Desired Skills information of an employee")
    private Set<Skill> desiredSkills;
    @Schema(description = "Desired Company information of an employee can store only 3 values")
    private SHLimitedList<String> desiredCompany;
    @Schema(description = "Meta information which cannot be indexed or searched upon")
    private SHLimitedMap<String, String> metadata;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String enterpriseId;
}
