package io.skyhive.veneer.parsers.dto.title;

import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 05/01/22
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class JobTitleResponse {
    private int categoryId;
    private String categoryName;
    private double score;
    private String id;
}
