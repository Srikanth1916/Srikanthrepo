package io.skyhive.veneer.parsers.dto.title;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobTitleNormalizationResponse {
    private String keywords;
    private String translatedTitle;
    private String language;
    private List<JobTitleResponse> results;
}
