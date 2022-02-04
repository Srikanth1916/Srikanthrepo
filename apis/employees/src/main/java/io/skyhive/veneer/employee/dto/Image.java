package io.skyhive.veneer.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Image dto.
 */
@Getter
@Setter
public class Image {
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String url;
    @Schema(accessMode = AccessMode.READ_ONLY)
    private String imageHandle;
}
