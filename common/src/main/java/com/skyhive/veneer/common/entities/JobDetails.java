package com.skyhive.veneer.common.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDetails {
    private String title;
    private String language;
    private String type;
    private String departmentId;
    private String skillLevel;
    private List<SkillRequirement> skills;
    private String description;
    private String referenceCode;
    private String referencePlatform;
}
