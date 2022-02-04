package io.skyhive.veneer.common.entities.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Location dto.
 */
@Getter
@Setter
public class LocationDto {
    private String formattedAddress;

    private String locationHandle;

    private String country;

    private String countryCode;

    private String state;

    private String stateShort;

    private String city;

    @JsonIgnore
    public String getCountryInfo(){
        if (StringUtils.isBlank(countryCode)) {return country;}
        return countryCode;
    }
}
