package io.skyhive.veneer.parsers.dto.employee;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Hobby.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hobby {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "Title of the Hobby")
    private String title;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String definition;

}
