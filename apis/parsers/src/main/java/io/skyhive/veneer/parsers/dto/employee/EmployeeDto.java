package io.skyhive.veneer.parsers.dto.employee;

import java.time.LocalDateTime;
import java.util.List;

import io.skyhive.veneer.parsers.enums.EmployeeType;
import io.skyhive.veneer.parsers.enums.MentorshipState;
import io.skyhive.veneer.parsers.enums.Roles;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Employee dto.
 *
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class EmployeeDto {

    private String id;
    private String accountId;
    private String b2CAccountId;
    private EmployeeType type;
    private LocalDateTime lastActivity;
    private ImageDto avatar;
    private String firstname;
    private String lastname;
    private String location;
    private String email;
    private String countryId;
    private String careerId;
    private String careerTitle;
    private DictionaryDto jobTitle;
    private String positionId;
    private double positionMatch;
    private String directManagerAccountId;
    private String directManagerName;
    private SkillDto[] skills;
    private int skillsCount;
    private SkillDto[] endorsedSkills;
    private SkillDto[] knowledgeGap;
    private SkillDto[] skillsUnderMentorship;
    private int knowledgeGapCount;
    private SkillDto[] knowledgeGapUnderMentorship;
    private Roles[] roles;
    private LocalDateTime dateHired;
    private LocalDateTime registrationDate;
    private MentorshipState lastUpdatedMentorshipState;
    private DictionaryDto[] skillCenterSubscriptions;
    private boolean activatedAccount;
    private EmployeeJobPreferencesDto preferences;
    private List<StudyDto> studies;
    private TrainingDto[] trainings;
    private LicenseDto[] licenses;
    private String[] patents;
    private String[] publications;
    private DictionaryDto[] hobbies;
    private ExperienceDto[] experience;
    private EmployeeSettingsDto settings;
    private boolean isInRequestedWfp;
    private boolean isInAnotherInProgressWfp;
    private boolean isInAnotherDraftWfp;
    private double matchScore; // TODO: remove!!! use MatchEmployeeDto
    private boolean profileFillOutNeeded;
    private boolean introductionTourCompleted;
    private String exportedSPAccountId; // User's Account ID on SP once they register
    private ExportDetailsDto exportDetails;
}

