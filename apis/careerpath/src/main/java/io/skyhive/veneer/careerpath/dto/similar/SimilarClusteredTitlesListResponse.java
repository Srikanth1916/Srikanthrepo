package io.skyhive.veneer.careerpath.dto.similar;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author krishna
 * @created 06/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class SimilarClusteredTitlesListResponse {
   private List<SimilarClusteredTitleItemDto> values;
}
