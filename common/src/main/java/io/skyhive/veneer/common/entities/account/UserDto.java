package io.skyhive.veneer.common.entities.account;

import io.skyhive.veneer.common.entities.location.LocationDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type User dto.
 *
 * @author krishna
 * @created 22 /10/21
 * @project skyhive -veeneer
 */
@Getter
@Setter
public class UserDto implements Serializable {

    private String id;

    private String email;

    private boolean isActivated;

    private String apiKey;

    private String firstname;

    private String lastname;

    private String avatar;

    private String seoUrl;

    private LocationDto location;

    private String postalCode;

    private String countryId;

    private boolean profileFillOutNeeded;
}
