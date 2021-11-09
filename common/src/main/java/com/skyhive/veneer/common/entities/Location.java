package com.skyhive.veneer.common.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String formattedAddress;
    private String addressLine1;
    private String getAddressLine2;
    private String municipality;
    private String postcode;
    private String country;
    private String country2;
    private String country3;
    private String state;
    private String stateShort;

}
