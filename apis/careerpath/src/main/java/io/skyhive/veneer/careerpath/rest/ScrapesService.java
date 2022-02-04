package io.skyhive.veneer.careerpath.rest;

import io.skyhive.veneer.careerpath.dto.similar.SimilarClusteredTitlesListResponse;
import io.skyhive.veneer.careerpath.dto.skills.ClusteredTitleStatsDto;
import io.skyhive.veneer.common.config.FeignMediaTypeSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@FeignClient(name = "scrapesService", url = "${scrapes.api.url}",
        configuration = FeignMediaTypeSupportConfig.class)
public interface ScrapesService {
    @GetMapping(value = "/api/clusteredTitle/skills/similarTitles")
    SimilarClusteredTitlesListResponse similarClusterTitles(@RequestParam("clusteredTitle") String clusteredTitle,
                                                            @RequestParam(
                                                                    "statisticsType") String statisticsType,
                                                            @RequestParam("countryCode") String countryCode,
                                                            @RequestParam("threshold") Double threshold,
                                                            @RequestParam("take") int take);
    @GetMapping(value = "/api/clusteredTitle/skills/{statisticsType}/{title" +
            "}/{countryCode}")
    ClusteredTitleStatsDto getSkillsByTitle(@PathVariable("statisticsType") String statisticsType, @PathVariable("title") String title,
                                            @PathVariable("countryCode") String country);
}
