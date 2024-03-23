package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.CityMaster;
import com.innoclique.identity.service.CityMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cityMaster")
public class CityMasterController {
    @Autowired
    CityMasterService cityMasterService;
    @PostMapping("/getCitysByState")
    public List<CityMaster> getCitysByState(@RequestBody CityMaster cityMaster) {
        return cityMasterService.getCitysByState(cityMaster);


    }


}
