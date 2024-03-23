package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.License;
import com.innoclique.identity.responses.LicenseResponse;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.service.PatientLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/license")
public class LicenseController {
    @Autowired
    public PatientLicenseService patientLicenseService;

    @CrossOrigin
    @PostMapping("/patientLicense")
    public StatusResponse getPatientById(@RequestBody License request){
        return patientLicenseService.generatePatientLicense(request);
    }

    @CrossOrigin
    @PostMapping("/validateLicenseKey")
        public LicenseResponse validateLicenseKey(@RequestBody License request) {
            return patientLicenseService.validateLicenseKey(request.getLicenseKey());

        }
}
