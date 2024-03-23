package com.innoclique.identity.service;

import com.innoclique.identity.DTO.License;
import com.innoclique.identity.DTO.LicenseValidation;
import com.innoclique.identity.entity.PatientDetailsEntity;
import com.innoclique.identity.entity.PatientLocationMappingEntity;
import com.innoclique.identity.repository.LocationRepository;
import com.innoclique.identity.repository.PatientDetailsRepository;
import com.innoclique.identity.repository.PatientLicenseValidationRepository;
import com.innoclique.identity.repository.PatientLocationMappingRepository;
import com.innoclique.identity.responses.LicenseResponse;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.utils.LicenseKeyGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
public class PatientLicenseService {
    @Autowired
    PatientDetailsRepository patientDetailsRepository;
    @Autowired
    LicenseKeyGenerator licenseKeyGenerator;
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PatientLocationMappingRepository patientLocationMappingRepository;

    @Autowired
    PatientLicenseValidationRepository patientLicenseValidationRepository;

    public StatusResponse generatePatientLicense(License license) {
        try {
            int patientId = license.getPatientId();
            String locationName = license.getLocationName();

            // Find location by name
            Integer locationId = locationRepository.findLocationIdByLocationName(locationName);

            // Retrieve patient details from the database
            Optional<PatientDetailsEntity> optionalPatient = patientDetailsRepository.findById(patientId);
            if (optionalPatient.isEmpty()) {
                log.info("Patient with ID {} not found.", patientId);
                return new StatusResponse(404, "Patient with ID " + patientId + " not found.");
            }

            // Validate input data against patient details
            PatientDetailsEntity patient = optionalPatient.get();
            String firstName = patient.getFirstName();
            String lastName = patient.getLastName();
            Date dateOfBirth = patient.getDateOfBirth();

            // Generate license key and update patient details
            String licenseKeyFinal = licenseKeyGenerator.generateLicenseKey(firstName, lastName, String.valueOf(dateOfBirth), locationName);

            // Save the generated license key
            PatientLocationMappingEntity patientLocationMappingEntity = new PatientLocationMappingEntity();
            patientLocationMappingEntity.setPatientId(patientId);
            patientLocationMappingEntity.setLocationId(locationId);
            patientLocationMappingEntity.setProviderId(license.getProviderId());
            patientLocationMappingEntity.setLicenseKey(licenseKeyFinal);
            patientLocationMappingEntity.setVisitDate(license.getVisitDate());
            patientLocationMappingRepository.save(patientLocationMappingEntity);

            // Log and return successful response
            log.info("License Key for {} {} is {}.", firstName, lastName, licenseKeyFinal);
            return new StatusResponse(200, "License Key generated successfully for " + firstName + " " + lastName + ".");
        } catch (Exception e) {
            log.error("An error occurred while generating license key.", e);
            return new StatusResponse(500, "Internal Server Error");
        }
    }


    public LicenseResponse validateLicenseKey(String licenseKey) {
        try {
            log.info("Validating license key: {}", licenseKey);
            List<LicenseValidation> results = patientLicenseValidationRepository.validateLicenseKey(licenseKey);

            // Check if results are null or empty
            if (results == null || results.isEmpty()) {
                log.warn("Invalid License Key or No results found for license key: {}", licenseKey);
                StatusResponse statusResponse = new StatusResponse();
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("Invalid License Key");
                return new LicenseResponse(statusResponse, null);
            }

            // Check if the first element of results is empty
            if (isEmptyPatientId(results.get(0))) {
                log.warn("Invalid License Key: Empty Patient ID for license key: {}", licenseKey);
                StatusResponse statusResponse = new StatusResponse();
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("Invalid License Key: Empty Patient ID");
                return new LicenseResponse(statusResponse, null);
            }

            log.info("User Details Fetch Successful for license key: {}", licenseKey);
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(200);
            statusResponse.setMessage("User Details Fetch Successful");
            return new LicenseResponse(statusResponse, results);
        } catch (Exception e) {
            log.error("An error occurred while validating license key: {}", licenseKey, e);
            StatusResponse statusResponse = new StatusResponse();
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
            return new LicenseResponse(statusResponse, null);
        }
    }




    private boolean isEmptyPatientId(LicenseValidation result) {
        // Assuming the patient ID is a field in the LicenseValidation
        return  result.getPatientId() == 0;
    }
}

