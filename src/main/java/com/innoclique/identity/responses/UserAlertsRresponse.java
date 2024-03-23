package com.innoclique.identity.responses;

import com.innoclique.identity.DTO.UserAlerts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Setter
@Getter
public class UserAlertsRresponse {

    private StatusResponse statusResponse;
    private List<UserAlerts> alertStatusList;
}
