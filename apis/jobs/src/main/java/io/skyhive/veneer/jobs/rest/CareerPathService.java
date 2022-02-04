package io.skyhive.veneer.jobs.rest;

import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.jobs.dto.skill.ClusteredTitleStatsDto;
import io.skyhive.veneer.jobs.dto.skill.SkillsComparisonRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author krishna
 * @created 18/01/22
 * @project skyhive-veeneer
 */


public interface CareerPathService {
    @GetMapping(value = "/skills/{title}")
    ClusteredTitleStatsDto getClusterTitleStats(@PathVariable("title") String title, @RequestParam("country") String country);

    @PostMapping(value = "/compare")
    SkillsMatchPercentageResponse compareSkills(@RequestBody SkillsComparisonRequest skillsComparisonRequest);

}
