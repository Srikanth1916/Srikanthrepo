package io.skyhive.veneer.employee.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.skyhive.veneer.employee.dto.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

/**
 * @author krishna
 * @created 27/12/21
 * @project skyhive-veeneer
 */
@Getter
@Setter
public class LocationRequest extends Location {
    @Schema(description = "Radius in Kms to search from the location point",
            defaultValue = "20")
    @Min(value = 0, message = "radius should be greater than or " +
            "equal to 0")
    private int radius = 20;
    @Schema(description = "Longitude point of a given co-ordinates")
    private Double longitude;
    @Schema(description = "Latitude point of a given co-ordinates")
    private Double latitude;

    @JsonIgnore
    public boolean makeApiCall(){
        return latitude == null || longitude == null;
    }
}
