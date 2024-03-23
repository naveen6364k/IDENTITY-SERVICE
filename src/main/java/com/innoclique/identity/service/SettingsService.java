package com.innoclique.identity.service;

import com.innoclique.identity.DTO.*;
import com.innoclique.identity.entity.AlertConfigEntity;
import com.innoclique.identity.entity.LocationDetailsEntity;
import com.innoclique.identity.entity.UserEntity;
import com.innoclique.identity.entity.UserLocationEntity;
import com.innoclique.identity.repository.*;
import com.innoclique.identity.responses.AlertConfigResponse;
import com.innoclique.identity.responses.FetchUsersReponse;
import com.innoclique.identity.responses.StatusResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class SettingsService {
    @Autowired
    private AlertConfigRepository alertConfigRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SecurityGroupRepository securityGroupRepository;

    public AlertConfigResponse alertConfig() {
        Iterable<AlertConfigEntity> alertConfigs = alertConfigRepository.findAll();

        // Create a map to store the JSON structure
        Map<String, Map<String, Integer>> jsonStructure = new HashMap<>();

        for (AlertConfigEntity config : alertConfigs) {
            String testName = config.getName();
            String alertType = config.getDescription();
            int score = config.getDescriptionValue();

            // If the test name does not exist in the JSON structure, create a new entry
            jsonStructure.putIfAbsent(testName, new HashMap<>());

            // Get the map for the test name and add the alert type and score
            Map<String, Integer> alertMap = jsonStructure.get(testName);
            alertMap.put(alertType, score);
        }

        // Convert the JSON structure to a JSON string
        try {
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(200);
            statusResponse.setMessage("Fetch Alert Configurations Successful");
            log.info("Successfully fetched");
            return new AlertConfigResponse(statusResponse, jsonStructure);
        } catch (Exception e) {
            // Log the exception

            log.error("Failed to generate JSON structure", e);

            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(400);
            statusResponse.setMessage("Failed to Fetch Configurations");
            return new AlertConfigResponse(statusResponse, jsonStructure);
        }
    }

    public StatusResponse addUser(User user, LocationMapping locationMapping) {
        try {
            // Convert User to UserEntity
            UserEntity userEntity = convertToEntity(user);

            UserEntity existingUser = userRepository.findByFirstNameAndLastName(userEntity.getFirstName(), userEntity.getLastName());
            if (existingUser != null) {
                log.info("User with the same first name and last name already exists");
                return new StatusResponse(400, "User with the same first name and last name already exists");
            }
            // Save userEntity to the database
            UserEntity savedUser = userRepository.save(userEntity);

            // Save default location if provided
            DefaultLocation defaultLocationDTO = locationMapping.getDefaultLocation();
            if (defaultLocationDTO != null) {
                LocationDetailsEntity defaultLocation = locationRepository.findById(defaultLocationDTO.getLocId()).orElse(null);
                if (defaultLocation != null) {
                    UserLocationEntity userLocationEntity = new UserLocationEntity();
                    userLocationEntity.setUser(savedUser);
                    userLocationEntity.setLocation(defaultLocation);
                    userLocationEntity.setDefaultFlag(defaultLocationDTO.getIsDefault());
                    userLocationRepository.save(userLocationEntity);
                } else {
                    return new StatusResponse(400, "Default location not found");
                }
            }

            // Save allowed locations if provided
            List<AllowedLocation> allowedLocations = locationMapping.getAllowedLocations();
            if (allowedLocations != null && !allowedLocations.isEmpty()) {
                for (AllowedLocation allowedLocationDTO : allowedLocations) {
                    LocationDetailsEntity allowedLocation = locationRepository.findById(allowedLocationDTO.getLocId()).orElse(null);
                    if (allowedLocation != null) {
                        UserLocationEntity userLocationEntity = new UserLocationEntity();
                        userLocationEntity.setUser(savedUser);
                        userLocationEntity.setLocation(allowedLocation);
                        userLocationEntity.setDefaultFlag(allowedLocationDTO.getIsDefault());
                        userLocationRepository.save(userLocationEntity);
                    } else {
                        return new StatusResponse(400, "Allowed location not found");
                    }
                }
            }
            log.info("User Inserted Successfully");
            return new StatusResponse(200, "User Inserted Successfully");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new StatusResponse(400, "Failed to insert user: " + e.getMessage());
        }
    }

    public StatusResponse updateUser(User user, LocationMapping locationMapping) {
        try {
            // Convert User to UserEntity
            UserEntity userEntity = convertToEntity(user);

            // Check if the user exists
            UserEntity existingUser = userRepository.findById(user.getUserId()).orElse(null);
            if (existingUser == null) {
                log.info("User not found for update");
                return new StatusResponse(404, "User not found for update");
            }

            // Update userEntity with the existing user's ID
            userEntity.setUserId(existingUser.getUserId());

            // Save updated userEntity to the database
            UserEntity updatedUser = userRepository.save(userEntity);

            // Update default location if provided
            DefaultLocation defaultLocationDTO = locationMapping.getDefaultLocation();
            if (defaultLocationDTO != null) {
                LocationDetailsEntity defaultLocation = locationRepository.findById(defaultLocationDTO.getLocId()).orElse(null);
                if (defaultLocation != null) {
                    // Check if the user already has a default location set
                    UserLocationEntity existingDefaultLocation = userLocationRepository.findByUserAndDefaultFlag(updatedUser, 1);
                    if (existingDefaultLocation != null) {
                        existingDefaultLocation.setLocation(defaultLocation);
                        userLocationRepository.save(existingDefaultLocation);
                    } else {
                        // If no default location exists, create a new one
                        UserLocationEntity userLocationEntity = new UserLocationEntity();
                        userLocationEntity.setUser(updatedUser);
                        userLocationEntity.setLocation(defaultLocation);
                        userLocationEntity.setDefaultFlag(defaultLocationDTO.getIsDefault());
                        userLocationRepository.save(userLocationEntity);
                    }
                } else {
                    return new StatusResponse(400, "Default location not found");
                }
            }

            // Update allowed locations if provided
            List<AllowedLocation> allowedLocations = locationMapping.getAllowedLocations();
            if (allowedLocations != null && !allowedLocations.isEmpty()) {
                for (AllowedLocation allowedLocationDTO : allowedLocations) {
                    LocationDetailsEntity allowedLocation = locationRepository.findById(allowedLocationDTO.getLocId()).orElse(null);
                    if (allowedLocation != null) {
                        // Check if the user already has this allowed location set
                        UserLocationEntity existingAllowedLocation = userLocationRepository.findByUserAndLocation(updatedUser, allowedLocation);
                        if (existingAllowedLocation != null) {
                            existingAllowedLocation.setDefaultFlag(allowedLocationDTO.getIsDefault());
                            userLocationRepository.save(existingAllowedLocation);
                        } else {
                            // If no allowed location exists, create a new one
                            UserLocationEntity userLocationEntity = new UserLocationEntity();
                            userLocationEntity.setUser(updatedUser);
                            userLocationEntity.setLocation(allowedLocation);
                            userLocationEntity.setDefaultFlag(allowedLocationDTO.getIsDefault());
                            userLocationRepository.save(userLocationEntity);
                        }
                    } else {
                        return new StatusResponse(400, "Allowed location not found");
                    }
                }
            }
            log.info("User Updated Successfully");
            return new StatusResponse(200, "User Updated Successfully");
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new StatusResponse(400, "Failed to update user: " + e.getMessage());
        }
    }

    public FetchUsersReponse fetchUsersList() {
        List<UserEntity> users = userRepository.findAll();
        List<FetchUser> fetchUsers = new ArrayList<>();

        for (UserEntity user : users) {
            String securityGroup = securityGroupRepository.findGroupNameBySecurityGroupId(user.getSecurityGroupId());
            String locationName = getLocationNameForUser(user);
            FetchUser fetchUser = new FetchUser();
            fetchUser.setEmailAddress(user.getEmailAddress());
            fetchUser.setFirstName(user.getFirstName());
            fetchUser.setLastName(user.getLastName());
            fetchUser.setLocationName(locationName);
            fetchUser.setSecurityGroup(securityGroup);
            fetchUsers.add(fetchUser);

        }
        StatusResponse statusResponse = new StatusResponse(200,"Users list fetched successfully");
        // Assuming StatusResponse is returned from this method
        return new FetchUsersReponse(statusResponse,fetchUsers);
    }

    private String getLocationNameForUser(UserEntity user) {
        UserLocationEntity userLocationEntity = userLocationRepository.findByUserAndDefaultFlag(user, 1);
        if (userLocationEntity != null) {
            LocationDetailsEntity location = userLocationEntity.getLocation();
            if (location != null) {
                return location.getLocationName();
            }
        }
        return null; // or handle accordingly if location is not found
    }




    private UserEntity convertToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        // Mapping from DTO to Entity
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUserCode(user.getCode());
        userEntity.setPassword1(user.getPassword());
        userEntity.setPassword2(user.getConfirmPassword());
        userEntity.setEmailAddress(user.getEmailAddress());
        userEntity.setAlerts(user.getReceiveAlerts());
        userEntity.setUserTypeId(user.getUserTypeId());
        userEntity.setSecurityGroupId(user.getSecurityGroup());

        // Set other fields as needed

        return userEntity;
    }
}
