package io.skyhive.veneer.jobs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dictionary {
    @Schema(description = "Identification information ")
    @NotNull(message = "Id is mandatory")
    @EqualsAndHashCode.Include
    private String id;
    @Schema(description = "Title information")
    private String title;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int sortOrder = 0;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String definition;

}
