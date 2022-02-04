package io.skyhive.veneer.careerpath.dto.similar;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.skyhive.veneer.common.serializer.FractionToPercentageSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 06/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SimilarClusteredTitleItemDto {
    private String title;
    @JsonAlias("match")
    @JsonSerialize(using = FractionToPercentageSerializer.class)
    private double quality;
}
