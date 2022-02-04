package io.skyhive.veneer.careerpath.dto.skills;

import io.skyhive.veneer.careerpath.dto.StatisticsType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private StatisticsType type;
    private List<ClusteredSkill> skills;
    //private LocalDateTime lastGenerated;
}
