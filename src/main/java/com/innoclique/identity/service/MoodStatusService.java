package com.innoclique.identity.service;


import com.innoclique.identity.DTO.MoodStatus;
import com.innoclique.identity.entity.MoodLableEntity;
import com.innoclique.identity.entity.MoodstatusEntity;
import com.innoclique.identity.repository.MoodLableRepository;
import com.innoclique.identity.repository.MoodStatusRepository;
import com.innoclique.identity.repository.PatientMoodMoniterRepository;
import com.innoclique.identity.responses.MoodStatusResponse;
import com.innoclique.identity.responses.StatusResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class MoodStatusService {
    @Autowired
    MoodStatusRepository moodStatusRepository;
    @Autowired
    MoodLableRepository moodLableRepository;
    @Autowired
    PatientMoodMoniterRepository patientMoodMoniterRepository;

    public MoodStatusResponse getWishMoods(MoodStatus moodStatus) {
        log.info("getAllMoodStatus Method Started in MoodStatusService");
        List<MoodStatus> resultList = null;
        StatusResponse statusResponse = new StatusResponse();
        String wishMessage = null;
        try {
            // Get the time from the moodStatus
            String time = moodStatus.getTime();
            log.info("String time is {}", time);

            // Get the wish message based on the time
            wishMessage = getWish(time);
            log.info("WishMessage Fetch Success  {}", wishMessage);

            // Get all MoodstatusEntity entities from the repository
            List<MoodstatusEntity> moodStatuses = moodStatusRepository.findAll();

            // Convert the MoodstatusEntity entities to DTOs
            resultList = toDTOList(moodStatuses);

            // Check if the resultList is not null and not empty
            if (resultList != null && !resultList.isEmpty()) {
                // Set the status code and message for a successful response
                statusResponse.setStatusCode(200);
                statusResponse.setMessage("MoodStatus Fetch Successfully");
                // Return a MoodStatusResponse with the statusResponse, wishMessage, and resultList
                return new MoodStatusResponse(statusResponse, wishMessage, resultList);
            } else {
                // Set the status code and message for a failed response
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("MoodStatus Fetch Failed");
            }
        } catch (Exception e) {
            // Set the status code and message for an internal server error
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
            // Print the exception stack trace
            log.info("Exception is " + e);
        }
        // Return a MoodStatusResponse with the statusResponse, wishMessage, and resultList
        return new MoodStatusResponse(statusResponse, wishMessage, resultList);
    }

    // Method to get the wish message based on the time
    public String getWish(String stringtime) {
        if (stringtime == null) {
            return null; // Return null if the input string is null
        }
        log.info("getWish Method Started ");
        // Parse the input string to LocalTime
        LocalTime time = LocalTime.parse(stringtime);
        log.info("Converted Time Is {}", time);

        // Get all MoodLableEntity entities from the repository
        List<MoodLableEntity> moodLableEntities = moodLableRepository.findAll();
        String wish = null;
        LocalTime after16 = LocalTime.of(16, 0);
        // Iterate through the MoodLableEntity entities
        for (MoodLableEntity moodLableEntity : moodLableEntities) {
            // Check if the current time is before the time in the MoodLableEntity entity
            if (time.isBefore(moodLableEntity.getTime().toLocalTime())) {
                // Set the wish message to the wish in the MoodLableEntity entity
                wish = moodLableEntity.getWish();
                // Break out of the loop
                break;
            } else if (time.isAfter(after16)) {
                wish = "GOODEVENING";
            }
        }
        // Return the wish message
        return wish;
    }

    public MoodStatusResponse getAllMoodStatus() {
        List<MoodStatus> resultList = null;
        StatusResponse statusResponse = new StatusResponse();
        try {
            List<MoodstatusEntity> moodStatuses = moodStatusRepository.findAll();
            resultList = toDTOList(moodStatuses);
            if (resultList != null && !resultList.isEmpty()) {
                // Set the status code and message for a successful response
                statusResponse.setStatusCode(200);
                statusResponse.setMessage("MoodStatus Fetch Successfully");
            } else {
                // Set the status code and message for a failed response
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("MoodStatus Fetch Failed");
            }
        } catch (Exception e) {
            // Set the status code and message for an internal server error
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
            // Print the exception stack trace
            log.info("Exception is " + e);
        }
        // Return a MoodStatusResponse with the statusResponse and resultList
        return new MoodStatusResponse(statusResponse, resultList);
    }

    private MoodStatus toDTO(MoodstatusEntity original) {
        MoodStatus dto = new MoodStatus();
        BeanUtils.copyProperties(original, dto);
        return dto;
    }

    public List<MoodStatus> toDTOList(List<MoodstatusEntity> originalList) {
        List<MoodStatus> dtoList = new ArrayList<>();
        for (MoodstatusEntity moodStatus : originalList) {
            dtoList.add(toDTO(moodStatus));
        }
        return dtoList;
    }
}