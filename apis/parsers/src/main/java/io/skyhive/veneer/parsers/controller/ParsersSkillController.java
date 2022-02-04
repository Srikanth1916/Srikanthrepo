package io.skyhive.veneer.parsers.controller;

import io.skyhive.veneer.common.ml.skill.SkillExtractionRequest;
import io.skyhive.veneer.common.ml.skill.SkillExtractionResponse;
import io.skyhive.veneer.common.rest.MLService;
import io.skyhive.veneer.parsers.dto.skill.SkillParseRequest;
import io.skyhive.veneer.parsers.dto.skill.SkillParseResponse;
import io.skyhive.veneer.parsers.dto.title.JobTitleNormalizationResponse;
import io.skyhive.veneer.parsers.mapper.SkillParseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@RestController
@Slf4j
public class ParsersSkillController {
    private final SkillParseMapper skillParseMapper;
    private final MLService mlService;
    @Autowired
    public ParsersSkillController(SkillParseMapper skillParseMapper,
                                  MLService mlService) {
        this.skillParseMapper = skillParseMapper;
        this.mlService = mlService;
    }
    @PostMapping(value = "/skills")
    public SkillParseResponse extractSkills(@RequestBody SkillParseRequest skillParseRequest) {
        SkillExtractionRequest skillExtractionRequest =
                skillParseMapper.toSkillExtractionRequest(skillParseRequest);
       SkillExtractionResponse skillExtractionResponse =
               mlService.extractSkillsV2(skillExtractionRequest);
       return skillParseMapper.fromSkillExtractionResponse(skillExtractionResponse);
    }

    @GetMapping(value = "/jobtitle/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JobTitleNormalizationResponse normalizeJobTitle(@PathVariable(
            "title") String title) {
       return skillParseMapper.toJobTitleNormalization(mlService.normalizeJobTitle(title));
    }
}
