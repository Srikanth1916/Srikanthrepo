package io.skyhive.veneer.parsers.mapper;

import io.skyhive.veneer.common.ml.skill.SkillExtractionRequest;
import io.skyhive.veneer.common.ml.skill.SkillExtractionResponse;
import io.skyhive.veneer.parsers.dto.skill.SkillParseRequest;
import io.skyhive.veneer.parsers.dto.skill.SkillParseResponse;
import io.skyhive.veneer.parsers.dto.title.JobTitleNormalizationResponse;
import org.mapstruct.Mapper;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Mapper(uses = SkillMapper.class)
public interface SkillParseMapper {
    SkillExtractionRequest toSkillExtractionRequest(SkillParseRequest skillParseRequest);
    SkillParseResponse fromSkillExtractionResponse(SkillExtractionResponse skillExtractionResponse);
    JobTitleNormalizationResponse toJobTitleNormalization(io.skyhive.veneer.common.ml.title.JobTitleNormalizationResponse jobTitleNormalizationResponse);
}
