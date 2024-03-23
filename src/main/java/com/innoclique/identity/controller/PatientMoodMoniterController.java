package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.PatientMoodMoniter;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.service.PatientMoodMoniterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/PatientMoodMoniterEntity")
public class PatientMoodMoniterController {
    @Autowired
    PatientMoodMoniterService patientMoodMoniterService;
    @CrossOrigin
    @PostMapping("/insertMoodStatus")
    public StatusResponse insertMoodStatus(@RequestBody PatientMoodMoniter patientMoodMoniter) {
        return  patientMoodMoniterService.insertMoodStatus(patientMoodMoniter);
    }
}

