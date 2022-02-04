package io.skyhive.veneer.careerpath.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.skyhive.veneer.careerpath.dto.SkillsComparisonRequest;
import io.skyhive.veneer.careerpath.dto.StatisticsType;
import io.skyhive.veneer.careerpath.dto.similar.SimilarClusteredTitlesListResponse;
import io.skyhive.veneer.careerpath.dto.skills.ClusteredTitleStatsDto;
import io.skyhive.veneer.careerpath.mapper.MatchingSkillMapper;
import io.skyhive.veneer.careerpath.rest.ScrapesService;
import io.skyhive.veneer.common.db.mongo.entity.DictionaryWithComplexity;
import io.skyhive.veneer.common.exception.NotFoundException;
import io.skyhive.veneer.common.ml.title.JobTitleNormalizationResponse;
import io.skyhive.veneer.common.ml.title.JobTitleResponse;
import io.skyhive.veneer.common.responses.SkillsMatchPercentageResponse;
import io.skyhive.veneer.common.rest.MLService;
import io.skyhive.veneer.common.services.DictionaryService;
import io.skyhive.veneer.common.services.MatchingService;
import io.skyhive.veneer.models.es.MatchingSkill;
import lombok.extern.slf4j.Slf4j;

/**
 * The Type Career Path Controller.
 *
 * @author jayaram
 * @created 06/12/21
 * @project skyhive -veeneer
 */
@RestController
@Slf4j
public class CareerPathController {

    @Autowired
    private MLService mlService;

    @Autowired
    private ScrapesService scrapesService;
    
    @Autowired
	private DictionaryService dictionaryService;
    
    @Autowired
    private MatchingSkillMapper matchingSkillMapper;



    @GetMapping("/similar")
    public SimilarClusteredTitlesListResponse getSimilarTitles(@RequestParam("title") String title,
                                                               @RequestParam(
                                                                       "country") String country,
                                                               @RequestParam(
                                                                       value
                                                                               = "resultsPerPage", defaultValue = "10") int take) throws NotFoundException {
      JobTitleNormalizationResponse jobTitleNormalizationResponse =
              mlService.normalizeJobTitle(title);
        Optional<JobTitleResponse> jobTitleResponseOptional =
                jobTitleNormalizationResponse.getResults().stream().filter(jobTitleResponse -> jobTitleResponse.getScore()>0.6).findFirst();
        if(jobTitleResponseOptional.isPresent()){
            JobTitleResponse jobTitleResponse = jobTitleResponseOptional.get();
            String clusterTitle = jobTitleResponse.getCategoryName();
           return scrapesService.similarClusterTitles(clusterTitle,
                    StatisticsType.JobMarketStatistic.toString(), country,
                   null, take);
        }
        throw new NotFoundException("Unknown JobTitle");
    }

    @GetMapping("/skills/{title}")
    public ClusteredTitleStatsDto getSkillsByTitle(@PathVariable(
            "title") String title,
                                                   @RequestParam("country") String country) throws NotFoundException {
        JobTitleNormalizationResponse jobTitleNormalizationResponse =
                mlService.normalizeJobTitle(title);
        Optional<JobTitleResponse> jobTitleResponseOptional =
                jobTitleNormalizationResponse.getResults().stream().filter(jobTitleResponse -> jobTitleResponse.getScore()>0.6).findFirst();
        if(jobTitleResponseOptional.isPresent()){
            JobTitleResponse jobTitleResponse = jobTitleResponseOptional.get();
            String clusterTitle = jobTitleResponse.getCategoryName();
            return scrapesService.getSkillsByTitle(StatisticsType.JobMarketPercentageStatistic.toString(), clusterTitle
                    , country);
        }
        throw new NotFoundException("Unknown JobTitle");
    }
    
    @PostMapping(value="/compare", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SkillsMatchPercentageResponse compareSkills(@RequestBody SkillsComparisonRequest skillsComparisonRequest) {

		Set<MatchingSkill> fromSkill = matchingSkillMapper.toMatchingSkill(skillsComparisonRequest.getFrom());
        getIdsFromSkills(fromSkill);
        Set<MatchingSkill> toSkill = matchingSkillMapper.toMatchingSkill(skillsComparisonRequest.getTo());
        getIdsFromSkills(toSkill);
        return MatchingService.caliculateMatchPercentage(fromSkill, toSkill);
    }

    private void getIdsFromSkills(Set<MatchingSkill> skills) {
        DictionaryWithComplexity defaultDictionary =
                DictionaryWithComplexity.getDefaultDictionary();
        Set<String> toSkillIds = skills.stream().map(skill -> skill.getId())
                .collect(Collectors.toSet());

        Map<String, DictionaryWithComplexity> dictionaryWithComplexityMap1 = dictionaryService.getByIds(toSkillIds);
        skills.forEach(matchingSkill -> matchingSkill.setComplexity(dictionaryWithComplexityMap1
                .getOrDefault(matchingSkill.getId(), defaultDictionary).getComplexity()));
    }

}
