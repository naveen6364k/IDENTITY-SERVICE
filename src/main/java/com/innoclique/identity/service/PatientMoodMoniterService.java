package com.innoclique.identity.service;


import com.innoclique.identity.DTO.PatientMoodMoniter;
import com.innoclique.identity.entity.PatientMoodMoniterEntity;
import com.innoclique.identity.repository.PatientMoodMoniterRepository;
import com.innoclique.identity.responses.StatusResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PatientMoodMoniterService {

    @Autowired
    PatientMoodMoniterRepository patientMoodMoniterRepository;

    public StatusResponse insertMoodStatus(PatientMoodMoniter dto) {

        StatusResponse statusResponse = new StatusResponse();
        try {
            // Extract mood status ID from the first entry in the moodMaster list
            int moodstatusId = dto.getMoodStatusID();
            if (moodstatusId != 0) {
                // Create a new instance of PatientMoodMoniterEntity
                PatientMoodMoniterEntity patientMoodMoniterEntity = new PatientMoodMoniterEntity();
                // Set PLID from the moodStatusDTO
                patientMoodMoniterEntity.setPlid(dto.getPlid());
                patientMoodMoniterEntity.setMoodStatusID(moodstatusId);
                // Save patient mood monitor data to the repository and return the result
                patientMoodMoniterRepository.save(patientMoodMoniterEntity);
                statusResponse.setMessage("MoodStatus Inserted Successfully");
                statusResponse.setStatusCode(200);
            } else {
                statusResponse.setMessage("MoodStatus was not Inserted invalid ID");
                statusResponse.setStatusCode(400);
            }

        } catch (Exception e) {
            statusResponse.setMessage("Internal Server Error");
            statusResponse.setStatusCode(500);
            // Print stack trace in case of exception
            log.info("Exception is " + e);
        }
        return statusResponse;
    }
}