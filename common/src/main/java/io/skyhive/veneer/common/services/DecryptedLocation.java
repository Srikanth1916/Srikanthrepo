package io.skyhive.veneer.common.services;

import lombok.Getter;
import lombok.Setter;

/**
 * @author krishna
 * @created 20/12/21
 * @project skyhive-veeneer
 */

@Setter
@Getter
public class DecryptedLocation {
    private String googlePlaceId;
    private DecryptedComponents components;
    private double latitude;
    private double longitude;
}

@Getter
@Setter
class DecryptedComponents {
    private String streetNumber;
    private String route;
    private String locality;
    private String state;
    private String stateShort;
    private String country;
    private String postalCode;
}