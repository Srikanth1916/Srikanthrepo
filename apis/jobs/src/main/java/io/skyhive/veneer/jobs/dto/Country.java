package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * The type Country.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Country {
    @Schema(description = "ISO two letter country code", required = true, example = "US")
    @NotBlank(message = "Country two letter name is mandatory")
    private String twoLetterName;
    @Schema(description = "Country Symbol", example = "$")
    private String currencySymbol;
    @Schema(description = "Currency code", example = "USD")
    private String currencyCode;
    @Schema(description = "Country name", example = "United States of America")
    private String title;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
}
