package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.Location;
import com.innoclique.identity.entity.LocationDetailsEntity;
import com.innoclique.identity.repository.LocationRepository;
import com.innoclique.identity.responses.EditLocationDetailsResponse;
import com.innoclique.identity.responses.LocationResponse;
import com.innoclique.identity.responses.ProviderResponse;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationRepository locationRepository;

    @CrossOrigin
    @GetMapping("/getAllLocations")
    public LocationResponse getAllLocations() {
        return locationService.getAllLocations();
    }

    @CrossOrigin
    @PostMapping("/editLocations")
    public EditLocationDetailsResponse editLocation(@RequestBody Location location) {
        try {
            return locationService.editLocations(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/updateLocation")
    public EditLocationDetailsResponse saveLocation(@RequestBody Location location) {

        return locationService.updateLocation(location);
    }

    @CrossOrigin
    @PostMapping("/addLocation")
    public StatusResponse addLocation(@RequestBody LocationDetailsEntity location) {
        return locationService.addLocation(location);
    }

    @CrossOrigin
    @GetMapping("/getAllProviders")
    public ProviderResponse getAllProviders() {
        return locationService.getAllProviders();
    }


}
