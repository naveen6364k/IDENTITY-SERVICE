package com.innoclique.identity.controller;

import com.innoclique.identity.responses.UserAlertsRresponse;
import com.innoclique.identity.service.UserAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserAlerts")
public class AlertStatusController {
    @Autowired
    UserAlertsService userAlertsService;

    @CrossOrigin
    @GetMapping("/alertStatus/getAllAlerts")
    public UserAlertsRresponse getAllAlerts() {
        return userAlertsService.getAllAlerts();

    }


}
