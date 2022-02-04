package io.skyhive.veneer.jobs.controller;

import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.common.services.MatchingService;
import io.skyhive.veneer.jobs.dto.skill.ClusteredTitleStatsDto;
import io.skyhive.veneer.jobs.dto.skill.SkillsComparisonRequest;
import io.skyhive.veneer.jobs.mapper.MatchingSkillMapper;
import io.skyhive.veneer.jobs.rest.CareerPathService;
import io.skyhive.veneer.jobs.rest.ScrapesService;
import io.skyhive.veneer.jobs.services.JobEsService;
import io.skyhive.veneer.jobs.services.JobMongoService;
import io.skyhive.veneer.models.es.MatchingSkill;
import io.skyhive.veneer.models.mongo.job.Job;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author krishna
 * @created 18/01/22
 * @project skyhive-veeneer
 */

@RestController
public class JobComparisonController {
    @Autowired
    private JobMongoService jobService;

    @Autowired
    private ScrapesService scrapesService;

    @Autowired
    private MatchingSkillMapper matchingSkillMapper;

    // TODO: Need to use careerpath service and use service discovery for
    //  this api
    @GetMapping("/{id}/compare")
    public SkillsMatchPercentageResponse compare(@RequestHeader(value = "X-Enterprise-Id") String enterpriseId,
                                                 @PathVariable("id") String jobId,
                                                 @RequestParam("title") String title) throws NotFoundException {
        Job job = jobService.findById(jobId, enterpriseId);
        String country = ObjectUtils.defaultIfNull(job.getCountry(),
                ObjectUtils.defaultIfNull(job.getLocationTime().getLocation().getCountry(), "CA"));
        ClusteredTitleStatsDto clusterTitleStats =
                scrapesService.getSkillsByTitle("jobMarketStatistic", title,
                        country);
        Set<MatchingSkill> fromSkills =
                matchingSkillMapper.toEsMatchingSkill(job.getSkills());
        Set<MatchingSkill> toSkills =
                matchingSkillMapper.toMatchingSkill(clusterTitleStats.getSkills());
        SkillsMatchPercentageResponse skillsMatchPercentageResponse =
         MatchingService.caliculateMatchPercentage(fromSkills, toSkills);
        return skillsMatchPercentageResponse;
    }
}
