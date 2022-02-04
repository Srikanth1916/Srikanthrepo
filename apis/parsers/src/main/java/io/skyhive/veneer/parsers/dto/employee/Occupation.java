package io.skyhive.veneer.parsers.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Occupation.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Occupation {

    private String id;
    private String title;
    private String titleValue;

}

