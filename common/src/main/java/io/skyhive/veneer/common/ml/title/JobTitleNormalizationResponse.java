package io.skyhive.veneer.common.ml.title;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Keywords")
    private String keywords;
    @JsonProperty("TranslatedTitle")
    private String translatedTitle;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Results")
    private List<JobTitleResponse> results;
}
