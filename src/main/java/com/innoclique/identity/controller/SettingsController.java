package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.AddUser;
import com.innoclique.identity.DTO.LocationMapping;
import com.innoclique.identity.DTO.User;
import com.innoclique.identity.responses.AlertConfigResponse;
import com.innoclique.identity.responses.FetchUsersReponse;
import com.innoclique.identity.responses.StatusResponse;
import com.innoclique.identity.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @CrossOrigin
    @GetMapping("/getAlertConfigurations")
    public AlertConfigResponse getAlertConfig(){
        return settingsService.alertConfig();
    }

    @CrossOrigin
    @PostMapping("/addUser")
    public StatusResponse addUser(@RequestBody AddUser addUser){
        User userDetails = addUser.getUserDetails().get(0);
        LocationMapping locationMapping = addUser.getLocationMapping();
        return settingsService.addUser(userDetails,locationMapping);
    }

    @CrossOrigin
    @PostMapping("/updateUser")
    public StatusResponse updateUser(@RequestBody AddUser addUser){
        User userDetails = addUser.getUserDetails().get(0);
        LocationMapping locationMapping = addUser.getLocationMapping();
        return settingsService.updateUser(userDetails,locationMapping);
    }

    @CrossOrigin
    @GetMapping("/fetchUsers")
    public FetchUsersReponse fetchUsers(){
        return settingsService.fetchUsersList();
    }

}
