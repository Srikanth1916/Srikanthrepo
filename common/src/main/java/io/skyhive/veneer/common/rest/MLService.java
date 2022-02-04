package io.skyhive.veneer.common.rest;

import io.skyhive.veneer.common.config.FeignMediaTypeSupportConfig;
import io.skyhive.veneer.common.ml.skill.SkillExtractionRequest;
import io.skyhive.veneer.common.ml.skill.SkillExtractionResponse;
import io.skyhive.veneer.common.ml.title.JobTitleNormalizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@FeignClient(name = "mlservice", url = "${mlservice.api.url}", configuration = FeignMediaTypeSupportConfig.class)
public interface MLService {
    @PostMapping(value = "/extract-skill-aggregator/skill_v2", produces =
            MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    SkillExtractionResponse extractSkillsV2(@RequestBody  SkillExtractionRequest extractionRequest);

    @GetMapping(value = "/job-title-normalization/title_v3_mapper/{title}")
    JobTitleNormalizationResponse normalizeJobTitle(@PathVariable("title") String title);
}
