package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.StateMaster;
import com.innoclique.identity.service.StateMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/StateMasterEntity")
public class StateMasterController {
    @Autowired
    StateMasterService stateMasterService;
@GetMapping("/getAllStates")
    public List<StateMaster> getAllStates() {
        return stateMasterService.getAllStates();
    }
}
