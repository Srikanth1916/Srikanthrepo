package com.skyhive.veneer.common.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Jobs {
    private String id;
    private Country country;
    private JobDetails details;
    private LocationTime locationTime;
    private Compensation compensation;
    private List<JobHistory> jobHistory;
}
