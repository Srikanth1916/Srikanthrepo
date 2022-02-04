package io.skyhive.veneer.parsers.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Image dto.
 * @author jayaram
 * @created 03/01/2022
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class ImageDto {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String url;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String imageHandle;
}

