package io.skyhive.veneer.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 17/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class Degree {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "Title of the degree persuded by an employee")
    private String title;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int sortOrder;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String definition;

}

