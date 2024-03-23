package com.innoclique.identity.service;

import com.innoclique.identity.DTO.UserAlerts;
import com.innoclique.identity.repository.UserAlertRepository;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.responses.UserAlertsRresponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAlertsService {
    @Autowired
    UserAlertRepository userAlertRepository;

    public UserAlertsRresponse getAllAlerts() {
        StatusResponse statusResponse = new StatusResponse();
        List<UserAlerts> userAlerts = new ArrayList<>();
        try {

            userAlerts = userAlertRepository.findAll();
            if (!userAlerts.isEmpty()) {
                statusResponse.setStatusCode(200);
                statusResponse.setMessage("AlertStatus Fetch Success");
            } else {
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("AlertStatus Fetch Failed");
            }
        } catch (Exception e) {
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
        }
        return new UserAlertsRresponse(statusResponse, userAlerts);
    }
}


