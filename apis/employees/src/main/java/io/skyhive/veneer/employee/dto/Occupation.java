package io.skyhive.veneer.employee.dto;

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
public class Occupation {
    private String id;
    private String title;
    private String titleValue;

}

