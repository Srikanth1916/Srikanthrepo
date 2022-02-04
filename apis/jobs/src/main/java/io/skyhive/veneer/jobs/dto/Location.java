package io.skyhive.veneer.jobs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Location.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    @Schema(description = "Formatted address if provided overrides all other values")
    private String formattedAddress;
    @Schema(description = "Address line 1 information of employee address")
    private String addressLine1;
    @Schema(description = "Address line 2 information of employee address")
    private String addressLine2;
    @Schema(description = "Municipality information of employee address")
    private String municipality;
    @Schema(description = "Postcode information of employee address")
    private String postcode;
    @Schema(description = "ISO two letter country code of employee address")
    @NotNull
    private String country;
    @Schema(description = "ISO three letter country code of employee address")
    private String country3;
    @Schema(description = "State information of employee address")
    private String state;
    @Schema(description = "State short code of employee address")
    private String stateShort;
    @Schema(description = "Location handle is skyhive encoded location which takes lat/lon ",
            example = "zlGRkTEdwMmUIDM3X9ymrS9wlGOyj/aNow57+P5j2L6qUe7sNI2cgS/SKmxHG/EYMCkmCq8b7s83pMVab6R" +
                    "TBC0pPS6oQSwLKCPln8HB1RuPmP+Q34y9yrHUiw08ZBe7pN7yKhI0IwI3vzwMXSqoaz+lMCfDNbRSniEOtifMEo" +
                    "YnIsIPYN/61+Hb4FzS7mP07+nrl1zfPuDI+lWttxziBhGAbbT30jlMbQ1o2hAMGLJYhl0jTrb6YsL6O7IALkrI3BX" +
                    "4vDOKbiLD2NZIhkSKWM3+RPTMruVxr9A4oNrTzTtZj/ftHU4grr5Nh1lDPBa0+1e9aRZuu9CqP/QTu6K6gg==")
    private String locationHandle;

    /**
     * Gets full address.
     *
     * @return the full address
     */
    @JsonIgnore
    public String getFullAddress() {
        if (StringUtils.isBlank(formattedAddress)) {
            return Stream.of(addressLine1, addressLine2, municipality, state,
                            country)
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.joining(","));
        }
        return formattedAddress;
    }

}
