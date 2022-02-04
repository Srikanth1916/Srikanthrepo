package io.skyhive.veneer.models.mongo.candidate;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Certificate {
    private String id;
    private String title;

}

