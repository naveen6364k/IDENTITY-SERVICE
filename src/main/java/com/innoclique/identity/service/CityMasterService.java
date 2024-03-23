package com.innoclique.identity.service;

import com.innoclique.identity.DTO.CityMaster;
import com.innoclique.identity.entity.CityMasterEntity;
import com.innoclique.identity.repository.CityMasterRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class CityMasterService {
    @Autowired
    CityMasterRepository cityMasterRepository;
    public List<CityMaster> getCitysByState(CityMaster cityMaster) {
        log.info("Fetching cities by state for stateId: {}", cityMaster.getStateId());
        List<CityMaster> result =cityMasterRepository.findByStateId(cityMaster.getStateId());
        log.debug("Found {} cities for stateId: {}", result.size(), cityMaster.getStateId());
     return result;
    }
}
