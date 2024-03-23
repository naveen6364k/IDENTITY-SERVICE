package com.innoclique.identity.service;

import com.innoclique.identity.DTO.Location;
import com.innoclique.identity.DTO.Provider;
import com.innoclique.identity.entity.LocationDetailsEntity;
import com.innoclique.identity.repository.LocationRepository;
import com.innoclique.identity.repository.ProviderRepository;
import com.innoclique.identity.responses.EditLocationDetailsResponse;
import com.innoclique.identity.responses.LocationResponse;
import com.innoclique.identity.responses.ProviderResponse;
import com.innoclique.identity.responses.StatusResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;
    @Autowired
    private ProviderRepository providerRepository;

    public Page<Location> query(Location vO) {
        throw new UnsupportedOperationException();
    }

    public LocationResponse getAllLocations() {
        Integer totalRecords = null;
        // Retrieve all location details from the database
        List<LocationDetailsEntity> location = locationRepo.findAll();
        totalRecords = location.size();
        // Convert the list of LocationDetailsEntity to a list of Location
        List<Location> finalList = toDTOList(location);
        // Check if the finalList is null or empty
        if (finalList == null || finalList.isEmpty()) {
            // Create a status response with a 400 status code and a "Bad request" message
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(400);
            statusResponse.setMessage("Bad request");

            // Create a LocationResponse with the status response and the final list
            return new LocationResponse(statusResponse, null, finalList);
        } else {
            // Create a status response with a 200 status code and a "Location Details Fetch Successfully" message
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(200);
            statusResponse.setMessage("Location Details Fetch Successfully");

            // Create a LocationResponse with the status response and the final list
            return new LocationResponse(statusResponse, totalRecords, finalList);
        }
    }


    public EditLocationDetailsResponse editLocations(Location location) {
        LocationDetailsEntity locationDetails = new LocationDetailsEntity();
        Location result = new Location();
        StatusResponse statusResponse = new StatusResponse();
        try {
            locationDetails = locationRepo.findByLocationId(location.getLocationId());
            if (locationDetails == null) {
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("Invalid LocationId");
                return new EditLocationDetailsResponse(statusResponse, result);
            }
            statusResponse.setStatusCode(200);
            statusResponse.setMessage("Location fetch success");
            result = toDTO(locationDetails);
        } catch (Exception e) {
            log.info("Exception is {}", e);
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
        }
        return new EditLocationDetailsResponse(statusResponse, result);

    }

    public EditLocationDetailsResponse updateLocation(Location locationDTO) {
        // Retrieve the LocationDetailsEntity object based on the provided locationID
        LocationDetailsEntity location = locationRepo.findByLocationId(locationDTO.getLocationId());
        // Update the location details based on the provided Location
        location.setCode(locationDTO.getCode());
        location.setLocationName(locationDTO.getLocationName());
        location.setAddress(locationDTO.getAddress());
        location.setCity(locationDTO.getCity());
        location.setProviderId(locationDTO.getProviderId());
        location.setState(locationDTO.getState());
        location.setZipCode(locationDTO.getZipCode());
        location.setIsActive(locationDTO.getIsActive());

        // Save the updated location details
        LocationDetailsEntity result = locationRepo.save(location);

        // If the location is not saved successfully, return an error response
        if (result.getLocationId() == 0) {
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(400);
            statusResponse.setMessage("Error updating  location details");
            log.info("Error updating  location details for {} ", locationDTO.getLocationName());

            return new EditLocationDetailsResponse(statusResponse, locationDTO);
        }
        // Return a success response
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatusCode(200);
        statusResponse.setMessage("Location Updated Successfully");
        log.info("{} Location Updated Successfully ", locationDTO.getLocationName());
        return new EditLocationDetailsResponse(statusResponse, locationDTO);
    }


    public StatusResponse addLocation(LocationDetailsEntity location) {
        // Set default value clientId
        location.setClientId(3);
        // Save the location details
        LocationDetailsEntity result = locationRepo.save(location);

        // Check if the location was saved successfully
        if (result.getLocationId() != 0) {
            // Create a success response
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(200);
            statusResponse.setMessage("Location was saved successfully");
            log.info("Location {} Successfully saved", result.getLocationName());
            return statusResponse;
        } else {
            // Create an error response
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(400);
            statusResponse.setMessage("Location was not saved");
            log.info("Location was not saved");
            return statusResponse;
        }
    }


    private Location toDTO(LocationDetailsEntity original) {
        Location dto = new Location();
        BeanUtils.copyProperties(original, dto);
        return dto;
    }

    public List<Location> toDTOList(List<LocationDetailsEntity> originalList) {
        List<Location> dtoList = new ArrayList<>();
        for (LocationDetailsEntity location : originalList) {
            dtoList.add(toDTO(location));
        }
        return dtoList;
    }


    public ProviderResponse getAllProviders() {
        StatusResponse statusResponse = new StatusResponse();
       // List<Provider> providers = new ArrayList<>();
       List<Provider>result = providerRepository.getAllProviders();
        if (result == null) {
            statusResponse.setStatusCode(400);
            statusResponse.setMessage("Provider List is Empty");
            return new ProviderResponse(statusResponse, result);
        }
//        for (Object[] row : result) {
//            Provider provider = new Provider();
//            provider.setProviderId((Integer) row[0]);
//            provider.setProviderName((String) row[1]);
//            providers.add(provider);
//        }
        statusResponse.setStatusCode(200);
        statusResponse.setMessage("Provider List fetch Success");
        return new ProviderResponse(statusResponse, result);
    }
}
