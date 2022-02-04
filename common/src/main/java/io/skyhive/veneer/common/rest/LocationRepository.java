package io.skyhive.veneer.common.rest;

import io.skyhive.veneer.common.entities.location.LocationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Location service.
 *
 * @author krishna
 * @created 28 /10/21
 * @project skyhive -veeneer
 */
@FeignClient(name = "location", url = "${enterprise.api.url}")
public interface LocationRepository {
    /**
     * Gets location.
     *
     * @param countryCode the country code
     * @param address     the address
     * @return the location
     */
    @GetMapping(value = "/api/v1.0/location/getLocation")
    LocationDto getLocation(@RequestParam("countryCode") String countryCode, @RequestParam("addressOrPostalCode") String address);

    /**
     * Gets location.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the location
     */
    @GetMapping(value = "/api/v1.0/location/getByCoordinates")
    LocationDto getLocation(@RequestParam("latitude") double latitude,
                          @RequestParam("longitude") double longitude);
}