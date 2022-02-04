package io.skyhive.veneer.location.controller;

import io.skyhive.veneer.common.entities.location.LocationDto;
import io.skyhive.veneer.common.rest.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author krishna
 * @created 03/01/22
 * @project skyhive-veeneer
 */
@RestController
@Slf4j
public class LocationController {

    private final LocationRepository locationRepository;
    @Autowired
    public LocationController(final LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public LocationDto getLocation(@RequestParam("country") String country,
                            @RequestParam("address") String address){
       return locationRepository.getLocation(country, address);
    }
}
