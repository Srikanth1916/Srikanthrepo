package io.skyhive.veneer.jobs.rest;

import io.skyhive.veneer.common.config.FeignMediaTypeSupportConfig;
import io.skyhive.veneer.jobs.dto.skill.ClusteredTitleStatsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@FeignClient(name = "scrapesService", url = "${scrapes.api.url}",
        configuration = FeignMediaTypeSupportConfig.class)
public interface ScrapesService {
    @GetMapping(value = "/api/clusteredTitle/skills/{statisticsType}/{title" +
            "}/{countryCode}")
    ClusteredTitleStatsDto getSkillsByTitle(@PathVariable("statisticsType") String statisticsType, @PathVariable("title") String title,
                                            @PathVariable("countryCode") String country);
}
