package io.skyhive.veneer.parsers.dto.employee;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Dictionary dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class DictionaryDto {
    @Schema(description = "Identification information ")
    @NotNull(message = "Id is mandatory")
    private String id;
    @Schema(description = "Title information")
    private String title;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int sortOrder = 0;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String definition;
}

