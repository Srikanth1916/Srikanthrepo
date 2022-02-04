package io.skyhive.veneer.jobs.dto.skill;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 06/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class ClusteredTitleStatsDto {
    private String clusteredTitle;
    private String countryCode;
    private String id;
    private String type;
    private List<ClusteredSkill> skills;
}
